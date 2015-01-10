package com.avifro.services;

import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.List;

/**
 * Created by avifro on 1/9/15.
 */
public class MarkitOnDemandInformationProvider implements StockExchangeInformationProvider {

    private final static String BASE_URL = "http://dev.markitondemand.com/Api/v2/Quote?";

    @Override
    public List<UpdatesMessage> getTradableEntitiesUpdates(List<TradableEntity> tradableEntities) {
        return null;
    }
}
