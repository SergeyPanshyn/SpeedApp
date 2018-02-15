package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Completable

abstract class UseCaseCompletable(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var completable: Completable? = null

    fun executeCompletable(onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        if (completable == null) {
            completable = useCaseCompletable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { completable = null }
                    .doOnComplete { completable = null }
        }
        disposables.add(completable!!.subscribe({ onComplete() },{ onError(it) }))
    }

    protected abstract val useCaseCompletable: Completable
}
