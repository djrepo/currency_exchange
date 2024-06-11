package com.bbf.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConversionUtils {

    public static long convertToUnixTimestamp(long utcTimestamp){
        return utcTimestamp*1000000;
    }

    public static long convertToUtcTimestamp(long unixTimestamp){
        return unixTimestamp/1000000;
    }

    public static double normalizePrice(long unitPrice){
        log.info("unitPrice:"+unitPrice);
        log.info("normalizedUnitPrice:"+unitPrice/1000000d);
        return unitPrice/1000000d;
    }

    public static double normalizeQuantity(long quantity){
        return quantity/100d;
    }
}
