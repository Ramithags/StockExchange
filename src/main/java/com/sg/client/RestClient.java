package com.sg.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.model.ExchangeRate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class RestClient {

    // Exchange API which is used to get the updated stock exchange data kindly refer http://iextrading.com/ for more information
    private static final String EXCHANGE_API = "https://sandbox.iexapis.com/stable/fx/latest?symbols=USDCAD,USDGBP,USDJPY&token=Tsk_b0d51180b9914c7ebf967883ebf6325d";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public RestClient() {
        rest = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public Collection<ExchangeRate> get() throws IOException {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.getForEntity(EXCHANGE_API, String.class, HttpMethod.GET, requestEntity);
        setStatus(responseEntity.getStatusCode());
        return new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Collection<ExchangeRate>>() {
        });
    }

    private void setStatus(HttpStatus status) {
        this.status = status;
    }
}
