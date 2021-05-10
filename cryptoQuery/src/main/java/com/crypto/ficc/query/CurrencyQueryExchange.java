package com.crypto.ficc.query;

import com.crypto.ficc.model.TickerResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CurrencyQueryExchange {

    @Autowired
    private TickerQueryService tickerQueryService;

    public TickerResult queryTickerFor(String currency) {
        return tickerQueryService.queryCurrency(currency.toUpperCase());
    }

    public String queryTickersAll() {
        return tickerQueryService.queryAll();
    }
}
