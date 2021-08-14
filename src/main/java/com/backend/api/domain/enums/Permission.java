package com.backend.api.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Permission {
    ALL("", ""),
    GET_ALL("GET", "/.*/?"),
    POST_ALL("POST", "/.*/?"),
    UPDATE_ALL("PUT", "/.*/?"),
    DELETE_ALL("DELETE", "/.*/?"),
    PATCH_ALL("PATCH", "/.*/?"),

    GET_BOOKING("GET", "/booking"),
    POST_BOOKING("POST", "/booking"),
    UPDATE_BOOKING("PUT", "/booking"),
    DELETE_BOOKING("DELETE", "/booking"),
    GET_LAST_BOOKING("GET", "/booking/last-booking"),
    UPDATE_BOOKING_STATUS("PATCH", "/booking/[0-9]*/booking-status"),
    ADD_SUPPLIES("PATCH","/booking/[0-9]*/add-supplies"),
    ADD_COMMENT("PATCH", "/booking/[0-9]*/add-comment"),
    LINK_EMPLOYEE("PATCH", "/booking/[0-9]*/link-employee"),

    GET_CUSTOMER("GET", "/customer"),
    GET_CUSTOMER_BY_ID("GET", "/customer/[0-9]*/"),
    POST_CUSTOMER("POST", "/customer"),
    UPDATE_CUSTOMER("PUT", "/customer/[0-9]*/"),
    DELETE_CUSTOMER("DELETE", "/customer/[0-9]*/"),

    GET_EMPLOYEE("GET", "/employee"),
    GET_EMPLOYEE_BY_ID("GET", "/employee/[0-9]*/"),
    POST_EMPLOYEE("POST", "/employee"),
    UPDATE_EMPLOYEE("PUT", "/employee/[0-9]*/"),
    DELETE_EMPLOYEE("DELETE", "/employee/[0-9]*/"),

    GET_USER("GET", "/user"),
    GET_USER_BY_ID("GET", "/user/[0-9]*/"),
    POST_USER("POST", "/user"),
    UPDATE_USER("PUT", "/user(/[0-9]*/)?"),
    DELETE_USER("DELETE", "/user/[0-9]*/"),
    GET_LOGGED_IN_USER_DATA("GET", "/user/logged"),
    GET_AUTH("GET", "/user/auth"),

    GET_VEHICLE("GET", "/vehicle"),
    GET_VEHICLE_BY_ID("GET", "/vehicle/[0-9]*/"),
    POST_VEHICLE("POST", "/vehicle"),
    UPDATE_VEHICLE("PUT", "/vehicle/[0-9]*/"),
    DELETE_VEHICLE("DELETE", "/vehicle/[0-9]*/");

    private String method;
    private String uri;

    public static Permission find(String method, String uri) {
        Permission ret = null;
        for (Permission permission : values()) {
            if (permission.getMethod().equals(method) && uri.matches(permission.getUri())) {
                ret = permission;
            }
        }
        return ret;
    }

    public String getUri() {
        return uri + "/?";
    }

}
