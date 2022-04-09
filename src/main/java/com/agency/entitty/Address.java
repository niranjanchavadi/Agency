package com.agency.entitty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
 class Address {
    private String state;
    private String city;
    @Pattern(regexp = "[7-9]{1}[0-9]{9}", message = "Please Enter Valid PhoneNumber")
    private String phoneNumber;
}
