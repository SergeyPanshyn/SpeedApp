package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Observable
import io.reactivex.observers.ResourceObserver

abstract class UseCaseStream<T>(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var observable: Observable<T>? = null

    fun executeObservable(observer: ResourceObserver<T>) {
        if (observable == null) {
            observable = null
            observable = useCaseObservable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { observable = null }
                    .doOnComplete { observable = null }
                    .doOnDispose() { observable = null }
        }
        disposables.add(observable!!.subscribeWith(observer))
    }

    protected abstract val useCaseObservable: Observable<T>
}
