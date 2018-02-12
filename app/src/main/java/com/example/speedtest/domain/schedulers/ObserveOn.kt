package com.example.speedtest.domain.schedulers

import rx.Scheduler

interface ObserveOn {

    val scheduler: Scheduler
}
