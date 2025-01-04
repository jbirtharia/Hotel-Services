package com.hotel.apicalls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestAPICall {

    @Autowired
    private RestTemplate restTemplate;

    public <T> List<T> callAPIWithList(String url, HttpMethod method, ParameterizedTypeReference<List<T>> typeReference) {
        ResponseEntity<List<T>> response = restTemplate.exchange(url, method, null, typeReference);
        return response.getBody();
    }

    public <T> T callAPI(String url, HttpMethod method, ParameterizedTypeReference<T> typeReference) {
        ResponseEntity<T> response = restTemplate.exchange( url, method, null, typeReference);
        return response.getBody();
    }
}
