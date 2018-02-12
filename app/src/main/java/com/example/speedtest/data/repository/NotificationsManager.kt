package com.example.speedtest.data.repository

import android.app.Notification
import android.content.Context

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
interface NotificationsManager {

    fun buildForegroundNotification(context: Context): Notification

}