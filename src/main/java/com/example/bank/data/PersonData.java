package com.example.bank.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonData {

    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private Integer height;

    private Integer weight;

    private AddressData address;
}
