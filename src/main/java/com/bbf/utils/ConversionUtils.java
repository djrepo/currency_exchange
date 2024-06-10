package com.bbf.utils;

public class ConversionUtils {

    public static long convertToUnixTimestamp(long utcTimestamp){
        return utcTimestamp*1000000;
    }

    public static long convertToUtcTimestamp(long unixTimestamp){
        return unixTimestamp/1000000;
    }

    public static double calcPrice(long unitPrice, long quantity){
        return unitPrice/1000000*calcQuantity(quantity);
    }

    public static double calcQuantity(long quantity){
        return quantity/100;
    }
}
