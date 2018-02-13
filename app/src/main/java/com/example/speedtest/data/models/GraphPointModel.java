package com.example.speedtest.data.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */

public class GraphPointModel extends RealmObject {

    @PrimaryKey
    private int id;

    private long timestamp;

    private int speed;

    public GraphPointModel(long timestamp, int speed) {
        this.timestamp = timestamp;
        this.speed = speed;
    }

    public GraphPointModel() {}

    public void assignPrimaryKey() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<GraphPointModel> graphModels = realm.where(GraphPointModel.class);

        if (graphModels.findAll().isEmpty()) {
            this.id = 0;
        } else {
            this.id = graphModels.max("id").intValue() + 1;
        }
        realm.close();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
