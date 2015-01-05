package com.avifro.entities;

/**
 * Created by avifro on 1/5/15.
 */
public class UpdatesMessage {

    private String symbol;
    private double value;
    private double change;


    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
