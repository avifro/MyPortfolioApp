package com.avifro;

import com.avifro.entities.TradableEntity;

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

    private UpdatesProducer() {}
    
//    public static UpdatesProducer getInstance(BlockingQueue<? extends TradableEntity> blockingQueue) {
//
//    }

}
