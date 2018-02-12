package com.example.speedtest.domain.base

import rx.Subscriber

open class DefaultSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {}

    override fun onError(e: Throwable) {}

    override fun onNext(value: T) {}
}