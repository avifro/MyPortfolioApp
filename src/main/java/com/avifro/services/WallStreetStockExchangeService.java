package com.avifro.services;

import com.avifro.entities.StockExchangeEnum;
import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by avifro on 1/5/15.
 */
public class WallStreetStockExchangeService implements StockExchangeService {

    private MarkitOnDemandInformationProvider markitOnDemandInformationProvider = new MarkitOnDemandInformationProvider();

    @Override
    public Set<UpdatesMessage> checkForUpdates(List<TradableEntity> tradableEntities) {
        Set<UpdatesMessage> updatesMessages = new HashSet<>(tradableEntities.size());
        if (isActive()) {
            List<String> symbols = tradableEntities.stream()
                                                   .map(TradableEntity::getSymbol)
                                                   .collect(Collectors.toList());
            updatesMessages.addAll(markitOnDemandInformationProvider.checkForUpdatesBySymbols(symbols));
        }
        return updatesMessages;
    }

    @Override
    public boolean isActive() {
        return StockExchangeOpeningTimesHelper.getInstance().isOpen(StockExchangeEnum.WALL_STREET);
    }

}
