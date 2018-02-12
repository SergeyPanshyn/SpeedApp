package com.example.speedtest.data.repository

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.speedtest.R
import com.example.speedtest.presentation.MainActivity

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class NotificationsManagerImpl : NotificationsManager {
    override fun buildForegroundNotification(context: Context): Notification {
        val builder = getNotificationBuilder(context, context.getString(R.string.speed_app_is_running))
        val resultIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent = getPendingIntent(resultIntent, context)
        builder.setContentIntent(resultPendingIntent)

        return builder.build()
    }

    private fun getPendingIntent(resultIntent: Intent, context: Context): PendingIntent {
        return PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getNotificationBuilder(context: Context, msg: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.speed_app_is_running))
                .setAutoCancel(true)
                .setContentText(msg)
    }
}