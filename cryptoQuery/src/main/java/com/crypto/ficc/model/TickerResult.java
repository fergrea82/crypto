package com.crypto.ficc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TickerResult {
    private String symbol;
    private String price;
}
