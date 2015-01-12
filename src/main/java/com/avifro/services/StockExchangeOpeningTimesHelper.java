package com.avifro.services;

import com.avifro.entities.StockExchangeEnum;
import com.avifro.http.AvifroJerseyClient;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 11/01/2015
 * Time: 18:52
 */
public class StockExchangeOpeningTimesHelper {

    private final static String BASE_URL = "";
    private static StockExchangeOpeningTimesHelper stockExchangeOpeningTimesHelper;

    private AvifroJerseyClient httpClient;
    
    private StockExchangeOpeningTimesHelper() {
        httpClient = new AvifroJerseyClient(BASE_URL);
    }

    public static StockExchangeOpeningTimesHelper getInstance() {
        if (stockExchangeOpeningTimesHelper == null) {
            stockExchangeOpeningTimesHelper = new StockExchangeOpeningTimesHelper();
        }
        return stockExchangeOpeningTimesHelper;
    }


    public boolean isOpen(StockExchangeEnum stockExchangeEnum) {
        boolean open = false;
        switch (stockExchangeEnum) {
            case WALL_STREET:

                break;
            case TA:

        }
        return open;
    }

}
