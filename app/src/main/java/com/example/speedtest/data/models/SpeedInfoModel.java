package com.example.speedtest.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */

public class SpeedInfoModel extends RealmObject {

    @PrimaryKey
    private int id = 1;

    private int maxSpeed;

    private int totalDistance;

    private int currentSpeed;

    public SpeedInfoModel(int maxSpeed, int totalDistance, int currentSpeed) {
        this.maxSpeed = maxSpeed;
        this.totalDistance = totalDistance;
        this.currentSpeed = currentSpeed;
    }

    public SpeedInfoModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
