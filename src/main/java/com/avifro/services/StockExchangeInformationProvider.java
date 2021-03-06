package com.avifro.services;

import com.avifro.entities.UpdatesMessage;

import java.util.List;

/**
 * Created by avifro on 1/9/15.
 */
public interface StockExchangeInformationProvider {

    List<UpdatesMessage> checkForUpdatesBySymbols(List<String> symbols);

}
