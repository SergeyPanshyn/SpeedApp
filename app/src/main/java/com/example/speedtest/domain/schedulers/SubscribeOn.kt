package com.example.speedtest.domain.schedulers


import io.reactivex.Scheduler

interface SubscribeOn {

     val scheduler: Scheduler
}
