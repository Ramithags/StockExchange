package com.sg.controller;

import com.sg.model.Exchange;
import com.sg.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/exchange")
@Validated
@AllArgsConstructor
public class ExchangeController {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    private ExchangeService exchangeService;


    @GetMapping("/stocks/{symbol}")
    @ResponseStatus(OK)
    public ResponseEntity<Exchange> getExchangeDetails(@PathVariable String symbol) {
        logger.debug("Stock exchange symbol", symbol);
        return status(OK).body(exchangeService.getCurrencyRates(symbol));
    }
}
