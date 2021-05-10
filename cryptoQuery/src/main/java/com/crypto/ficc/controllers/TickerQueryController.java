package com.crypto.ficc.controllers;

import com.crypto.ficc.model.TickerResult;
import com.crypto.ficc.query.CurrencyQueryExchange;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("TickerQuery")
@RequestMapping("/tickerQuery")
@Slf4j
@Getter
@Setter
public class TickerQueryController {

    @Autowired
    private CurrencyQueryExchange currencyQueryExchange;

    @GetMapping(value="/ccy/{currency}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TickerResult> getCcy(@PathVariable("currency") String currency) {
        Optional<TickerResult> result = Optional.of(currencyQueryExchange.queryTickerFor(currency));
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value="/ccy/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        Optional<String> result = Optional.of(currencyQueryExchange.queryTickersAll());
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
