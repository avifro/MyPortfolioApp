package com.avifro.services;

import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by avifro on 1/5/15.
 */
public class TelAvivStockExchangeService implements StockExchangeService {

    @Override
    public Set<UpdatesMessage> checkForUpdates(List<TradableEntity> tradableEntities) {
        Set<UpdatesMessage> updatesMessages = new HashSet<>();
        if (isActive()) {

        }
        return updatesMessages;
    }

    @Override
    public boolean isActive() {

        return false;
    }
}
