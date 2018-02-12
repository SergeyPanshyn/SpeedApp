package com.example.speedtest.domain.base

import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Completable

abstract class UseCaseCompletable(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var completable: Completable? = null

    fun executeCompletable(onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        if (completable == null) {
            completable = useCaseCompletable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { completable = null }
                    .doOnCompleted { completable = null }
        }
        subscription = completable?.subscribe({ onComplete() }, { onError(it) })
    }


    protected abstract val useCaseCompletable: Completable
}
