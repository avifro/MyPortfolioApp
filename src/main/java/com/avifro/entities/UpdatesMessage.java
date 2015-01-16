package com.avifro.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by avifro on 1/5/15.
 */

@XmlRootElement(name = "StockQuote")
public class UpdatesMessage {

    private String name;

    private String symbol;

    private double change;

    private double changePercent;

    private double lastPrice;

    private String date;

    @XmlElement(name="Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChange() {
        return change;
    }

    @XmlElement(name="Change")
    public void setChange(double change) {
        this.change = change;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    @XmlElement(name="LastPrice")
    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    @XmlElement(name="Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getChangePercent() {
        return changePercent;
    }

    @XmlElement(name="ChangePercent")
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    @XmlElement(name = "Timestamp")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
