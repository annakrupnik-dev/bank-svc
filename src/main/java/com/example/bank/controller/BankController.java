package com.example.bank.controller;

import com.example.bank.data.PersonData;
import com.example.bank.data.PersonDataWrapper;
import com.example.bank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class BankController {

    private final static Logger logger =LoggerFactory.getLogger(BankController.class);

    @Autowired
    private BankService bankService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDataWrapper getAllPersons() {
        return bankService.getAllPersons();
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonData getPersonById(@PathVariable (value = "personId") Integer personId) {
        return bankService.getPersonById(personId);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDataWrapper getAllPersonsByNameCondition() {
        return bankService.getAllPersonsByNameCondition();
    }

    @GetMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDataWrapper getAllPersonsFromIsraelAndAboveAverage() {
        return bankService.getAllPersonsFromIsraelAndAboveAverage();
    }

}
