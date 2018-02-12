package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.subscriptions.Subscriptions

open class UseCase(protected var subscribeOn: SubscribeOn, protected var observeOn: ObserveOn) {
    protected var subscription = Subscriptions.empty()

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}
