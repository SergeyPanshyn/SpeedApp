package com.example.speedtest.domain.schedulers


import rx.Scheduler

interface SubscribeOn {

     val scheduler: Scheduler
}
