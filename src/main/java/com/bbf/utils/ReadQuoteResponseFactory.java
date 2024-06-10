package com.bbf.utils;

import com.bbf.model.QuoteEntity;
import com.bbf.model.json.ReadQuoteResponse;

public class ReadQuoteResponseFactory {

    public static ReadQuoteResponse createQuoteResponse(QuoteEntity entity){
        ReadQuoteResponse readQuoteResponse = new ReadQuoteResponse();
        readQuoteResponse.setTime(ConversionUtils.convertToUtcTimestamp(entity.getTime()));
        if (entity.getAsk()>0){
            readQuoteResponse.setType(QuoteType.ASK);
            readQuoteResponse.setPrice(ConversionUtils.calcPrice(entity.getAskVolume(),entity.getAsk()));
            readQuoteResponse.setVolume(ConversionUtils.calcQuantity(entity.getAsk()));
        } else {
            readQuoteResponse.setType(QuoteType.BID);
            readQuoteResponse.setPrice(ConversionUtils.calcPrice(entity.getBidVolume(),entity.getAsk()));
            readQuoteResponse.setVolume(ConversionUtils.calcQuantity(entity.getBid()));
        }
        return readQuoteResponse;
    }
}
