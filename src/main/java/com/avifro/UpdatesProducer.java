package com.avifro;

import com.avifro.entities.StockExchangeEnum;
import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;
import com.avifro.services.StockExchangeService;
import com.avifro.services.TelAvivStockExchangeService;
import com.avifro.services.WallStreetStockExchangeService;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:53
 */
public class UpdatesProducer {

    private static UpdatesProducer producer;

    private BlockingQueue<UpdatesMessage> blockingQueue;

    private Map<String, StockExchangeService> stockExchangeServices;

    private UpdatesProducer(BlockingQueue<UpdatesMessage> blockingQueue) {
        this.blockingQueue = blockingQueue;
        stockExchangeServices.put(StockExchangeEnum.TA.name(), new TelAvivStockExchangeService());
        stockExchangeServices.put(StockExchangeEnum.WALL_STREET.name(), new WallStreetStockExchangeService());
    }

    public static UpdatesProducer getInstance(BlockingQueue<UpdatesMessage> blockingQueue) {
        if (producer == null) {
            producer = new UpdatesProducer(blockingQueue);
        }
        return producer;
    }

    public void produce(List<TradableEntity> tradableEntities) {
        Set<UpdatesMessage> updatesMessages = new HashSet<>();
        tradableEntities.parallelStream()
                .collect(Collectors.groupingBy(TradableEntity::getOriginExchangeStock))
                .forEach((exchangeStockType, tradableEntitiesPerType) -> {
                    updatesMessages.addAll(stockExchangeServices.get(exchangeStockType).checkForUpdates(tradableEntitiesPerType));
                });
        blockingQueue.addAll(updatesMessages);
    }

}
