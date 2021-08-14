package com.backend.api.repositories;

import com.backend.api.domain.Booking;
import com.backend.api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findAllByDate(LocalDate date);

  //https://www.baeldung.com/jpa-limit-query-results
  List<Booking> findTop1ByCustomerOrderByIdDesc(Customer customer);
}
