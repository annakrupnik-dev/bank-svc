package com.example.bank.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressData {

    private Integer id;

    private String street;

    private String city;

    private String state;

    private Integer zipcode;
}