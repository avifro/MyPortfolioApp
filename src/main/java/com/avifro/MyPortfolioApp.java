package com.avifro;

import com.avifro.entities.TradableEntity;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:14
 */
public class MyPortfolioApp {

    private static MyPortfolioApp myPortfolioApp;

    private BlockingQueue<? extends TradableEntity> sharedBlockingQueue;
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
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(()-> checkForUpdates(), 0, 30, TimeUnit.MINUTES);
    }

    public void checkForUpdates() {
        // getting user configuration

        producer.
    }

}
