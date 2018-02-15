package com.example.speedtest.domain.schedulers

import io.reactivex.Scheduler

interface ObserveOn {

    val scheduler: Scheduler
}
