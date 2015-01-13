package com.avifro;

import com.avifro.entities.Share;
import com.avifro.entities.StockExchangeEnum;
import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:14
 */
public class MyPortfolioApp {

    private static MyPortfolioApp myPortfolioApp;

    private BlockingQueue<UpdatesMessage> sharedBlockingQueue;
    private UpdatesProducer producer;
    private UpdatesConsumer consumer;

    private MyPortfolioApp() {
        init();
    }

    public static MyPortfolioApp getInstance() {
        if (myPortfolioApp == null) {
            myPortfolioApp = new MyPortfolioApp();
        }
        return myPortfolioApp;
    }

    private void init() {
        sharedBlockingQueue = new LinkedBlockingQueue();
        producer = UpdatesProducer.getInstance(sharedBlockingQueue);
        consumer = UpdatesConsumer.getInstance(sharedBlockingQueue);
    }

    public void run() {
        // Consumer thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()-> consumer.consume());

        // Producer thread
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(()-> checkForUpdates(), 0, 30, TimeUnit.MINUTES);
    }

    public void checkForUpdates() {
        // getting user configuration from db
        // TODO temp solution
        List<TradableEntity> tradableEntities = new ArrayList<>();

        Share hpq = new Share();
        hpq.setName("Hewellet Packard");
        hpq.setOriginExchangeStock(StockExchangeEnum.WALL_STREET);
        hpq.setSymbol("HPQ");

        tradableEntities.add(hpq);

        producer.produce(tradableEntities);
    }

}
