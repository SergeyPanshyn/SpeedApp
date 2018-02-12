package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Observable
import rx.Subscriber

abstract class UseCaseStream<T>(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var observable: Observable<T>? = null

    fun executeObservable(subscriber: Subscriber<T>) {
        if (observable == null) {
            observable = null
            observable = useCaseObservable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { observable = null }
                    .doOnCompleted { observable = null }
                    .doOnUnsubscribe { observable = null }
        }
        subscription = observable?.subscribe(subscriber)
    }

    protected abstract val useCaseObservable: Observable<T>
}
