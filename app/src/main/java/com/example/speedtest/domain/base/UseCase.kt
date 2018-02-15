package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.disposables.CompositeDisposable

open class UseCase(protected var subscribeOn: SubscribeOn, protected var observeOn: ObserveOn) {

    protected var disposables = CompositeDisposable()

    fun unsubscribe() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
