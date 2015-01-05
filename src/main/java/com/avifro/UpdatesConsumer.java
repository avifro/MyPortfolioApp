package com.avifro;

import com.avifro.entities.TradableEntity;

import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:54
 */
public class UpdatesConsumer {

    private static UpdatesConsumer consumer;
    private BlockingQueue<? extends TradableEntity> blockingQueue;

    private UpdatesConsumer(BlockingQueue<? extends TradableEntity> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public static UpdatesConsumer getInstance(BlockingQueue<? extends TradableEntity> blockingQueue) {
        if (consumer == null) {
            consumer = new UpdatesConsumer(blockingQueue);
        }
        return consumer;
    }



}
