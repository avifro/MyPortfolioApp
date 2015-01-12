package com.avifro.services;

import com.avifro.entities.UpdatesMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avifro on 1/9/15.
 */
public class MarkitOnDemandInformationProvider implements StockExchangeInformationProvider {

    private final static String BASE_URL = "http://dev.markitondemand.com/Api/v2/Quote?";

    @Override
    public List<UpdatesMessage> checkForUpdatesBySymbols(List<String> symbols) {
        List<UpdatesMessage> updatesMessages = new ArrayList<>(symbols.size());
        symbols.forEach();
        return updatesMessages;
    }
}
