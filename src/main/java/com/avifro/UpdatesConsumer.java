package com.avifro;

import com.avifro.db.MongoConfigHelper;
import com.avifro.entities.UpdatesMessage;
import com.avifro.notifications.services.NotificationActionsService;
import com.avifro.notifications.services.ProwlActionsService;
import com.avifro.repositories.MyPortfolioMongoRepository;
import com.mongodb.DB;

import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 16:54
 */
public class UpdatesConsumer {

    private static UpdatesConsumer consumer;

    private PropertiesHandler propertiesHandler;
    private BlockingQueue<UpdatesMessage> blockingQueue;
    private NotificationActionsService notificationActionsService;
    private MyPortfolioMongoRepository repository;

    private UpdatesConsumer(BlockingQueue<UpdatesMessage> blockingQueue) {
        this.blockingQueue = blockingQueue;
        propertiesHandler = PropertiesHandler.getInstance();
        notificationActionsService = new ProwlActionsService(propertiesHandler.getProperty(PropertiesHandler.MY_APP_NAME_KEY),
                                                             propertiesHandler.getProperty(PropertiesHandler.PROWL_API_KEY));
        initRepository();
    }

    private void initRepository() {
        DB mongoDb = MongoConfigHelper.getDB(propertiesHandler.getProperty(PropertiesHandler.DB_HOST_KEY),
                                             propertiesHandler.getProperty(PropertiesHandler.DB_PORT_KEY),
                                             propertiesHandler.getProperty(PropertiesHandler.DB_NAME_KEY),
                                             propertiesHandler.getProperty(PropertiesHandler.DB_USER_NAME_KEY),
                                             propertiesHandler.getProperty(PropertiesHandler.DB_PASSWORD_KEY));
        repository = MyPortfolioMongoRepository.getInstance(mongoDb, propertiesHandler.getProperty(PropertiesHandler.DB_COLLECTION_KEY));
    }

    public static UpdatesConsumer getInstance(BlockingQueue<UpdatesMessage> blockingQueue) {
        if (consumer == null) {
            consumer = new UpdatesConsumer(blockingQueue);
        }
        return consumer;
    }

    public void consume() {
        try {
            while (true) {
                UpdatesMessage msg = blockingQueue.take();
                notificationActionsService.sendNotification(formatEventName(msg), formatDescription(msg));
                repository.insertMessage(msg);
            }
        } catch (InterruptedException e) {
        // Do nothing
        }
    }

    private String formatEventName(UpdatesMessage msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msg.getDate()).append(" - ")
                     .append(msg.getName()).append("(")
                     .append(msg.getSymbol()).append(")");
        return stringBuilder.toString();
    }

    private String formatDescription(UpdatesMessage msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("value: ").append(msg.getLastPrice()).append("$")
                     .append(", change: ").append(msg.getChangePercent());
        return stringBuilder.toString();
    }
}
