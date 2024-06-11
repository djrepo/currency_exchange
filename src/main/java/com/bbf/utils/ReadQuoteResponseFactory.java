package com.bbf.utils;

import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteResponse;

public class ReadQuoteResponseFactory {

    public static ReadQuoteResponse createQuoteResponse(QuoteEntity entity){
        ReadQuoteResponse readQuoteResponse = new ReadQuoteResponse();
        readQuoteResponse.setTime(ConversionUtils.convertToUtcTimestamp(entity.getTime()));
        if (entity.getAsk()>0){
            readQuoteResponse.setType(QuoteType.ASK);
            readQuoteResponse.setPrice(ConversionUtils.normalizePrice(entity.getAsk()));
            readQuoteResponse.setVolume(ConversionUtils.normalizeQuantity(entity.getAskVolume()));
        } else {
            readQuoteResponse.setType(QuoteType.BID);
            readQuoteResponse.setPrice(ConversionUtils.normalizePrice(entity.getBid()));
            readQuoteResponse.setVolume(ConversionUtils.normalizeQuantity(entity.getBidVolume()));
        }
        return readQuoteResponse;
    }
}
