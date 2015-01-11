package com.avifro.services;

import com.avifro.entities.StockExchangeEnum;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 11/01/2015
 * Time: 18:52
 */
public class StockExchangeOpeningTimesHelper {

    private static StockExchangeOpeningTimesHelper stockExchangeOpeningTimesHelper;

    private StockExchangeOpeningTimesHelper() {}

    private StockExchangeOpeningTimesHelper getInstance() {
        if (stockExchangeOpeningTimesHelper == null) {
            stockExchangeOpeningTimesHelper = new StockExchangeOpeningTimesHelper();
        }
        return stockExchangeOpeningTimesHelper;
    }


    public boolean isOpen(StockExchangeEnum stockExchangeEnum) {
        return false;
    }

}
