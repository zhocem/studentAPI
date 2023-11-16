package com.dovi.studentapi.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private AddressDTO addressDTO;
}
