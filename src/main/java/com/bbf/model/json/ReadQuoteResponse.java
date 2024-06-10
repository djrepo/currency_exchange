package com.bbf.model.json;

import com.bbf.utils.QuoteType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadQuoteResponse {

    private long time;
    private QuoteType type;
    private double price;
    private double volume;
}
