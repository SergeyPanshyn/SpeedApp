package com.example.speedtest.presentation.dashboard

import android.util.Log
import com.example.speedtest.domain.base.DefaultSubscriber
import com.example.speedtest.domain.speed.GetCurrentSpeedUseCase

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class DashboardPresenterImpl<T: DashboardPresenter.DashboardView>(private val getCurrentSpeedUseCase: GetCurrentSpeedUseCase): DashboardPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    override fun subscribeToCurrentSpeed() {
        getCurrentSpeedUseCase.executeObservable(object : DefaultSubscriber<Float>(){
            override fun onCompleted() {
                Log.d("onxGetSpeed", "Completed.")
            }

            override fun onError(e: Throwable) {
                Log.d("onxGetSpeed", "Err: $e")
            }

            override fun onNext(value: Float) {
                view?.showSpeed(value)
            }
        })
    }
}