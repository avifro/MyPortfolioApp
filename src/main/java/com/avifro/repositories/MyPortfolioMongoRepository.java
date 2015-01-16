package com.avifro.repositories;

import com.avifro.entities.UpdatesMessage;
import com.mongodb.*;

/**
 * Created by avifro on 11/15/14.
 */
public class MyPortfolioMongoRepository {

    private DB db;
    private String collectionName;

    private static MyPortfolioMongoRepository repository;

    private MyPortfolioMongoRepository(DB db, String collectionName){
        this.db = db;
        this.collectionName = collectionName;
        createCollection(collectionName);
    }

    public static MyPortfolioMongoRepository getInstance(DB db, String collectionName) {
        if (repository == null) {
            repository = new MyPortfolioMongoRepository(db, collectionName);
        }
        return repository;
    }


    private void createCollection(String collectionName) {
        if (db!= null && !db.collectionExists(collectionName)) {
            db.createCollection(collectionName, null);
        }
    }

    public WriteResult insertMessage(UpdatesMessage msg) {
        DBCollection coll = db.getCollection(collectionName);
        BasicDBObject dbObject = new BasicDBObject("name", msg.getName())
                .append("symbol", msg.getSymbol())
                .append("change", msg.getChange())
                .append("changePercent", msg.getChangePercent())
                .append("lastPrice", msg.getLastPrice())
                .append("date", msg.getDate());
        return coll.insert(dbObject);
    }
}
