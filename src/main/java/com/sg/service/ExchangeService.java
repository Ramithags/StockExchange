package com.sg.service;

import com.google.gson.JsonObject;
import com.sg.client.RestClient;
import com.sg.model.Exchange;
import com.sg.model.ExchangeRate;
import com.sg.repository.ExchangeRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    private ExchangeRepository exchangeRepository;

    private RestClient restClient;

    public Exchange getCurrencyRates(@NonNull String exchangeSymbol) {
        Exchange exchangeResponse = exchangeRepository.findByName(exchangeSymbol.toLowerCase());
        if (exchangeResponse != null) {
            return exchangeRepository.findByName(exchangeSymbol.toUpperCase());
        } else {
            logger.info("Cannot find symbol in cache");
        }
        return null;
    }

    @Scheduled(fixedRate = 60000)
    public void updateExchangeRates() throws IOException {
        restClient = new RestClient();
        Collection<ExchangeRate> exchangeRate = restClient.get();
        logger.info("Exchange rate", exchangeRate.stream().findFirst().get().getSymbol());
    }

    private void persistData(JsonObject exchange) {
        Exchange exchangeData = new Exchange();
        exchangeData.setName(exchange.get("symbol").getAsString());
        exchangeData.setLatestPrice(exchange.get("latestPrice").getAsDouble());
        exchangeData.setPreviousClose(exchange.get("previousClose").getAsDouble());
        exchangeRepository.save(exchangeData);
    }

}
