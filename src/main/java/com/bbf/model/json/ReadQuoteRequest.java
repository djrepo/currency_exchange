package com.bbf.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadQuoteRequest {
    @JsonProperty("from_time")
    private long fromTime;
    @JsonProperty("to_time")
    private long toTime;
    @JsonProperty("quote_type")
    private String quoteType;
}
