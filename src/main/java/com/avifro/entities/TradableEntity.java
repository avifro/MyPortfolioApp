package com.avifro.entities;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 14:47
 */
public abstract class TradableEntity {

    private String name;
    private String value;
    private String symbol;
    protected TradableEntityEnum type;
    private ExchangeStockEnum originExchangeStock;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract TradableEntityEnum getType();

    public void setType(TradableEntityEnum type) {
        this.type = type;
    }

    public ExchangeStockEnum getOriginExchangeStock() {
        return originExchangeStock;
    }

    public void setOriginExchangeStock(ExchangeStockEnum originExchangeStock) {
        this.originExchangeStock = originExchangeStock;
    }
}
