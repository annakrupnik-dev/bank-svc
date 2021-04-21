package com.example;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class JUnitTestUtil  {

    private String baseUrl = "http://localhost:8080";

    public String executePostRequest(String url, Object requestBody) {
        return runTest(url, requestBody, HttpMethod.POST);
    }

    public String executePutRequest(String url, Object requestBody) {
        return runTest(url, requestBody, HttpMethod.PUT);
    }

    public String executePutRequestWithCollections(String url,
                                                   Collection<?> collectionClass) {
        return runTest(url, collectionClass, HttpMethod.PUT);
    }

    public String executePatchRequest(String url, Object requestBody) {
        return runTest(url, requestBody, HttpMethod.PATCH);
    }

    public String executeGetRequest(String url) {
        return runTest(url, null, HttpMethod.GET);
    }

    public String executeDeleteRequest(String url) {
        return runTest(url, null, HttpMethod.DELETE);
    }

    private String runTest(String url, Object requestBody, HttpMethod httpMethod) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String convertObjectToJson = JsonUtils.convertObjectToJson(requestBody);

        HttpHeaders headers = setJsonAttributesToHeaders();

        HttpEntity<String> httpEntity = new HttpEntity<String>(convertObjectToJson, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(baseUrl + url, httpMethod, httpEntity, String.class);
        String responseBody = exchange.getBody();
        log(httpMethod, url);
        log(convertObjectToJson, exchange.getStatusCode(), responseBody);
        return responseBody;
    }

    private void log(HttpMethod httpMethod, String url) {
        System.out.println("FULL URL = " + baseUrl + url);
        System.out.println("KIT URL = " + url);
        System.out.println("HttpMethod = " + httpMethod.name());
    }

    private void log(String objectAsString, HttpStatus statusCode, String body) {
        System.out.println("input data = " + objectAsString);
        System.out.println("return code = " + statusCode);
        System.out.println("return body = " + body);
        System.out.println();
    }

    private HttpHeaders setJsonAttributesToHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

}

