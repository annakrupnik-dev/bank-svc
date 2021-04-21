package com.example.bank.service;

import com.example.bank.controller.BankController;
import com.example.bank.data.PersonData;
import com.example.bank.data.PersonDataWrapper;
import com.example.bank.exception.ResourceNotFoundException;
import com.example.bank.utils.ExchangeUtil;
import com.example.bank.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankServiceImpl implements BankService {
    private final static Logger logger = LoggerFactory.getLogger(BankController.class);

    private final ExchangeUtil exchangeUtil = new ExchangeUtil();

    @Override
    public PersonDataWrapper getAllPersons() {

        String url ="/addresses/persons";
        String body = exchangeUtil.executeGetRequest(url);

        return JsonUtils.fromJsonString(body,PersonDataWrapper.class);

    }

    @Override
    public PersonData getPersonById(int personId) {
        int statusNotFound=404;
        String url ="/addresses/persons/" + personId;
        String body="";
        try {
            body = exchangeUtil.executeGetRequest(url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == statusNotFound) {
                System.out.println((e.getMessage()));
                throw new ResourceNotFoundException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        PersonData   personData  = JsonUtils.fromJsonString(body,PersonData.class);
        return personData;
    }

    @Override
    public PersonDataWrapper getAllPersonsByNameCondition() {

        PersonDataWrapper response = new PersonDataWrapper();
        Stream<PersonData>  persons = getAllPersons().getPersons().stream();
        List<PersonData> filteredList = persons
                .filter(p -> p.getName().startsWith("A") || p.getName().startsWith("K"))
                .collect(Collectors.toList());

        response.setPersons(filteredList);

        return response;

    }

    @Override
    public PersonDataWrapper getAllPersonsFromIsraelAndAboveAverage() {

        PersonDataWrapper response = new PersonDataWrapper();

        List<PersonData> persons = getAllPersons().getPersons();
        Stream<PersonData>  personsStream = getAllPersons().getPersons().stream();

        int[] arr = new int[persons.size()];
        for (int i = 0; i < persons.size(); i++) {
            arr[i] = persons.get(i).getWeight();
        }
        double average = Arrays.stream(arr).average().orElse(0);

        List<PersonData> filteredList = personsStream
                .filter(p -> p.getWeight() > average && p.getAddress().getState().equals("Israel"))
                .collect(Collectors.toList());

        response.setPersons(filteredList);

        return response;

    }


}
