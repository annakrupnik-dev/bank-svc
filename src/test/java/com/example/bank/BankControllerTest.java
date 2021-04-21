package com.example.bank;

import com.example.JsonUtils;
import com.example.bank.data.PersonData;
import com.example.bank.data.PersonDataWrapper;
import com.example.JUnitTestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankControllerTest {

    private final JUnitTestUtil testUtil = new JUnitTestUtil();

    @Test
    public void getAllPersons()  {

        String url ="/persons";
        String body = testUtil.executeGetRequest(url);
        Assert.assertNotNull(body);

        PersonDataWrapper obj= JsonUtils.fromJsonString(body,PersonDataWrapper.class);

        Assert.assertTrue(obj.getPersons().size()>0);
    }

    @Test
    public void getAllPersonsByNameCondition()  {

        String url ="/persons/name";
        String body = testUtil.executeGetRequest(url);
        Assert.assertNotNull(body);

        PersonDataWrapper obj= JsonUtils.fromJsonString(body,PersonDataWrapper.class);

        Assert.assertTrue(obj.getPersons().size()>0);
    }

    @Test
    public void getAllPersonsFromIsraelAndAboveAverage()  {

        String url ="/persons/country";
        String body = testUtil.executeGetRequest(url);
        Assert.assertNotNull(body);

        PersonDataWrapper obj= JsonUtils.fromJsonString(body,PersonDataWrapper.class);

        Assert.assertTrue(obj.getPersons().size()>0);
    }

    @Test
    public void getPersonById()  {

        String url ="/persons/10";
        String body = testUtil.executeGetRequest(url);
        Assert.assertNotNull(body);

        PersonData obj= JsonUtils.fromJsonString(body,PersonData.class);
        int foundId =obj.getId();

        Assert.assertEquals(10, foundId);
    }

    @Test
    public void getPersonByIdNotFound()  {

        String url ="/persons/100";

        boolean thrown = false;
        int statusNotFound=404;
        int statusError=0;
        try {
            testUtil.executeGetRequest(url);
        } catch (HttpClientErrorException e) {
            thrown = true;
            statusError = e.getStatusCode().value();
            System.out.println(e.getMessage());
        }

        Assert.assertTrue(thrown);
        Assert.assertEquals(statusNotFound,statusError);
    }

}
