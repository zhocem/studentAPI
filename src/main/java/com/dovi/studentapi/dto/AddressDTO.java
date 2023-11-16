package com.dovi.studentapi.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long addressId;

    private String street;

    private String city;
}
