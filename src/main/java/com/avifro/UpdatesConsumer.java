package com.avifro;

import com.avifro.entities.TradableEntity;
import com.avifro.entities.UpdatesMessage;
import com.avifro.notifications.services.NotificationActionsService;
import com.avifro.notifications.services.ProwlActionsService;

import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:54
 */
public class UpdatesConsumer {

    private static UpdatesConsumer consumer;
    private BlockingQueue<UpdatesMessage> blockingQueue;
    private NotificationActionsService notificationActionsService;
    private PropertiesHandler propertiesHandler;

    private UpdatesConsumer(BlockingQueue<UpdatesMessage> blockingQueue) {
        this.blockingQueue = blockingQueue;
        propertiesHandler = PropertiesHandler.getInstance();
        notificationActionsService = new ProwlActionsService(propertiesHandler.getProperty(PropertiesHandler.MY_APP_NAME_KEY),
                                                             propertiesHandler.getProperty(PropertiesHandler.PROWL_API_KEY));
    }

    public static UpdatesConsumer getInstance(BlockingQueue<UpdatesMessage> blockingQueue) {
        if (consumer == null) {
            consumer = new UpdatesConsumer(blockingQueue);
        }
        return consumer;
    }

    public void consume() {
        UpdatesMessage msg;
        try {
            while (true) {
                msg = blockingQueue.take();
                notificationActionsService.sendNotification(formatMsg(msg), null);
            }
        } catch (InterruptedException e) {

        }
    }

    private String formatMsg(UpdatesMessage msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msg.getSymbol())
                     .append("- value: ")
                     .append(msg.getValue())
                     .append(", change: ")
                     .append(msg.getChange());
        return stringBuilder.toString();
    }
}
