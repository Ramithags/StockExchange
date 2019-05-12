package com.sg.service;

import com.google.gson.*;
import com.sg.client.RestClient;
import com.sg.model.Exchange;
import com.sg.repository.ExchangeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private RestClient restClient;

    public Exchange getCurrencyRates(String exchangeSymbol) {
        Exchange exchangeResponse = exchangeRepository.findBySymbol(exchangeSymbol.toUpperCase());
        if (exchangeResponse != null) {
            return exchangeRepository.findBySymbol(exchangeSymbol.toUpperCase());
        } else {
            System.out.println("Not found");
        }
        return exchangeResponse;
    }

    @Scheduled(fixedRate = 60000)
    public void updateStockQuotes() {
        restClient = new RestClient();
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(restClient.get());
        JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry : entries) {
            JsonObject abc = obj.getAsJsonObject(entry.getKey()).getAsJsonObject("quote");
            persistData(abc);
        }
    }

    private void persistData(JsonObject exchange) {
        Exchange exchangeData = new Exchange();
        exchangeData.setSymbol(exchange.get("symbol").getAsString());
        exchangeData.setName(exchange.get("companyName").getAsString());
        exchangeData.setLatestPrice(exchange.get("latestPrice").getAsDouble());
        exchangeData.setPreviousClose(exchange.get("previousClose").getAsDouble());
        exchangeRepository.save(exchangeData);
    }

}
