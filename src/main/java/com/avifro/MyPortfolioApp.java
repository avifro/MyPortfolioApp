package com.avifro;

import com.avifro.entities.TradableEntity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:14
 */
public class MyPortfolioApp {

    private static MyPortfolioApp myPortfolioApp;
    private static BlockingQueue<? extends TradableEntity> sharedBlockingQueue;
    private static UpdatesProducer producer;
    private static UpdatesConsumer consumer;

    private MyPortfolioApp() {}

    public static MyPortfolioApp getInstance() {
        if (myPortfolioApp == null) {
            myPortfolioApp = new MyPortfolioApp();
            init();
        }
        return myPortfolioApp;
    }

    private static void init() {
        sharedBlockingQueue = new LinkedBlockingQueue();

    }

    public void checkForUpdates() {

    }

}
