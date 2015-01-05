package com.avifro.services;

import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.List;
import java.util.Set;

/**
 * Created by avifro on 1/5/15.
 */
public interface StockExchangeService {

    Set<UpdatesMessage> checkForUpdates(List<TradableEntity> tradableEntities);




}
