package com.crypto.ficc.query;

import com.crypto.ficc.config.TickerApiProperties;
import com.crypto.ficc.model.TickerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
@Slf4j
public class TickerQueryService {

    @Autowired
    private TickerApiProperties tickerApiProperties;
    @Autowired
    private RestOperations restOperations;


    public TickerResult queryCurrency(String currency) {
        String path = tickerApiProperties.getHost()+tickerApiProperties.getEndpoints().get("get-ticker-by-currency")+currency;
        TickerResult result = restOperations.getForObject(path,TickerResult.class);
        return result;
    }

    public String queryAll() {
        String path = tickerApiProperties.getHost()+tickerApiProperties.getEndpoints().get("get-ticker-all");
        String result = restOperations.getForObject(path,String.class);
        return result;
    }
}
