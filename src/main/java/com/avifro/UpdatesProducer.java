package com.avifro;

import com.avifro.entities.ExchangeStockEnum;
import com.avifro.entities.TradableEntity;
import com.avifro.services.StockExchangeService;
import com.avifro.services.TelAvivStockExchangeService;
import com.avifro.services.WallStreetStockExchangeService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:53
 */
public class UpdatesProducer {

    private static UpdatesProducer producer;

    private BlockingQueue<? extends TradableEntity> blockingQueue;

    private Map<String, StockExchangeService> stockExchangeServices;

    private UpdatesProducer(BlockingQueue<? extends TradableEntity> blockingQueue) {
        this.blockingQueue = blockingQueue;
        stockExchangeServices.put(ExchangeStockEnum.TA.name(), new TelAvivStockExchangeService());
        stockExchangeServices.put(ExchangeStockEnum.WALL_STREET.name(), new WallStreetStockExchangeService());
    }

    public static UpdatesProducer getInstance(BlockingQueue<? extends TradableEntity> blockingQueue) {
        if (producer == null) {
            producer = new UpdatesProducer(blockingQueue);
        }
        return producer;
    }

    public checkForUpdates(List<TradableEntity> tradableEntities) {

        //todo can split according to type and then call in parallel once per earch type to stockExchangeService and finally merge

        tradableEntities.forEach((tradableEntity) -> {
            stockExchangeServices.get(tradableEntity.getOriginExchangeStock()).checkForUpdates(Arrays.asList(tradableEntity));
        });
    }

}
