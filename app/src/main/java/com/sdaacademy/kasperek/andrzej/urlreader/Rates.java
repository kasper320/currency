package com.sdaacademy.kasperek.andrzej.urlreader;

import java.util.HashMap;

/**
 * Created by dell1 on 2017-04-26.
 */

public class Rates {

    private String base;
    private String date;
    private HashMap rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap getRates() {
        return rates;
    }

    public void setRates(HashMap rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "Selling 1 " + base + "you get:" +
                "   - " + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
