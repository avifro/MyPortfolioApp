package com.avifro.services;

import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.List;

/**
 * Created by avifro on 1/9/15.
 */
public class BizportalInformationProvider implements StockExchangeInformationProvider {

    @Override
    public List<UpdatesMessage> checkForUpdatesBySymbols(List<String> symbols) {
        return null;
    }
}
