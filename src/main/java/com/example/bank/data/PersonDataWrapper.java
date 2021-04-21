package com.example.bank.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDataWrapper {

    private List<PersonData> persons = new ArrayList<>();
}
