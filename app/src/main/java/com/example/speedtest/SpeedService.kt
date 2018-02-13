package com.example.speedtest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.speedtest.data.repository.NotificationsManager
import com.example.speedtest.data.repository.SpeedCheckManager
import javax.inject.Inject

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SpeedService: Service() {

    companion object {
        val FOREGROUND_NOTIFICATION_ID = 11

        var IS_RUNNING = false
    }

    @Inject
    lateinit var notificationsManager: NotificationsManager

    @Inject
    lateinit var speedCheckManager: SpeedCheckManager

    override fun onCreate() {
        super.onCreate()
        IS_RUNNING = true
        SpeedApp.appComponent?.inject(this)

        speedCheckManager.runSpeedChecker()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_NOTIFICATION_ID,
                notificationsManager.buildForegroundNotification(this))

        return START_STICKY
    }

    override fun onDestroy() {
        stopForeground(true)
        IS_RUNNING = false
        speedCheckManager.clean()

        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}