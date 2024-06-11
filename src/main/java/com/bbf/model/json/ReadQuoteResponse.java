package com.bbf.model.json;

import com.bbf.utils.QuoteType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReadQuoteResponse {

    private long time;
    private QuoteType type;
    private double price;
    private double volume;
}
