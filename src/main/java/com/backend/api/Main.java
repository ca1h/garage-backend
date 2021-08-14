package com.backend.api;

import com.backend.api.domain.*;
import com.backend.api.domain.enums.Permission;
import com.backend.api.dtos.crud.create.CreateEmployeeDto;
import com.backend.api.enums.EngineType;
import com.backend.api.repositories.*;
import com.backend.api.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RequestMapping(name = "/api")
@AllArgsConstructor
public class Main implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final SupplyRepository supplyRepository;
    private final EmployeeService employeeService;
    private final BCryptPasswordEncoder pe;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = Role
                .builder()
                .name("ROLE_ADMIN")
                .permission(Permission.ALL)
                .build();

        Role customerRole = Role
                .builder()
                .name("ROLE_CUSTOMER")
                .permissions(List.of(
                        Permission.GET_LAST_BOOKING,
                        Permission.POST_BOOKING,
                        Permission.UPDATE_USER,
                        Permission.GET_LOGGED_IN_USER_DATA,
                        Permission.GET_AUTH,
                        Permission.GET_VEHICLE,
                        Permission.GET_VEHICLE_BY_ID,
                        Permission.POST_VEHICLE,
                        Permission.UPDATE_VEHICLE
                ))
                .build();

        Role staff = Role
                .builder()
                .name("ROLE_STAFF")
                .permission(Permission.ALL)
                .build();


        roleRepository.saveAll(List.of(admin, customerRole, staff));


        User user = User.
                builder()
                .email("admin")
                .password(pe.encode("admin"))
                .role(admin)
                .build();

        User customerUser = User.
                builder()
                .email("customer")
                .password(pe.encode("customer"))
                .role(customerRole)
                .build();

        User staffUser = User.
                builder()
                .email("staff")
                .password(pe.encode("staff"))
                .role(staff)
                .build();

        userRepository.saveAll(List.of(
                user,
                customerUser,
                staffUser
        ));

        employeeService.create(
            CreateEmployeeDto
                .builder()
                .userId(staffUser.getId())
                .name("Staff_01")
                .build()
        );

        Customer customer = Customer
                .builder()
                .name("Catherine")
                .phoneNumber("123234234")
                .user(customerUser)
                .build();

        customerRepository.save(customer);

        Vehicle vehicle = Vehicle
                .builder()
                .engineType(EngineType.DIESEL)
                .make("s")
                .licenceDetails("abc")
                .type("def")
                .customer(customer)
                .build();

        vehicleRepository.save(vehicle);

        initSupplies();
    }

    public void initSupplies() {
        List<String> names = List.of("Oil Filters", "Air Filters", "Wiper Blades", "Batteries", "Spark Plugs", "Fuel Filters", "Pollen Filters", "Glow Plugs", "Additional Truck Filters", "Brake Pads", "Brake Discs", "Brake Hoses", "Brake Cables", "ABS Sensors", "Brake Pads Fitting Kits", "ABS Sensor Rings", "Brake Shoes Fitting Kits", "Brake Drums", "Brake Fluid", "Brake Calipers", "Brake Light Switches", "Brake Master Cylinders", "Brake Caliper Parts", "Brake Power Regulators", "Brake Boosters and Vacuum Parts", "Lights", "Wing Mirrors", "Window Regulators", "Boot Struts Gas Springs", "Body Panels and Trims", "Truck Marker Lights", "Body Panels", "Bumper", "xBumpers", "Sealant", "Aerials", "Wiper Switches and Stalks", "Wiper Gear, window cleaner", "Wiper Bearing", "Windscreen Washer Fluid Jets", "Windscreen Washer Fluid Tanks", "Wiper Arm, headlight cleaning", "Window Wiper Systems", "Wiper Arm Caps", "Wiper Linkage, headlight cleaning", "Bonnets", "Headlight Wiper Blades", "Bonnet Gas Springs", "Airbag Clocksprings", "Bumpers", "Cruise Control Switches", "Wiper Arms", "Wiper Blade Rubber", "Grilles", "Wiper Linkage", "Jacking Points", "Wiper Motors", "Wiper System Parts", "Hazard Light Switches", "Wiper Blades", "Wings", "Tacho Shafts", "Window Crank", "Brake Pedal Rubbers");
        List<Float> values = List.of(60F, 50F, 30F, 80F, 30F, 50F, 35F, 70F, 40F, 80F, 70F, 70F, 30F, 150F, 200F, 80F, 100F, 100F, 50F, 120F, 40F, 150F, 100F, 100F, 250F, 50F, 30F, 80F, 40F, 70F, 80F, 60F, 100F, 150F, 30F, 80F, 30F, 30F, 30F, 50F, 50F, 30F, 150F, 100F, 60F, 500F, 200F, 120F, 180F, 80F, 70F, 40F, 30F, 30F, 120F, 650F, 240F, 780F, 70F, 40F, 310F, 70F, 40F, 40F);
        List<Supply> supplies = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            supplies.add(Supply
                    .builder()
                    .name(names.get(i))
                    .price(values.get(i))
                    .build());
        }
        supplyRepository.saveAll(supplies);
    }

}
