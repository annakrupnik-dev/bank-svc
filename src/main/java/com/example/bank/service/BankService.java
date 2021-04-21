package com.example.bank.service;

import com.example.bank.data.PersonData;
import com.example.bank.data.PersonDataWrapper;
import org.springframework.web.bind.annotation.PathVariable;

public interface BankService {
    PersonDataWrapper getAllPersons();
    PersonData getPersonById(int personId);
    PersonDataWrapper getAllPersonsByNameCondition();
    PersonDataWrapper getAllPersonsFromIsraelAndAboveAverage();
}
