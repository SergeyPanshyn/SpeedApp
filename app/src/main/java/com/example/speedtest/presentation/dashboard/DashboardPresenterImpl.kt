package com.example.speedtest.presentation.dashboard

import android.util.Log
import com.example.speedtest.data.models.SpeedInfoModel
import com.example.speedtest.domain.base.DefaultSubscriber
import com.example.speedtest.domain.speed.GetCurrentSpeedUseCase
import com.example.speedtest.domain.speed.GetSpeedInfoUseCase
import com.example.speedtest.domain.speed.SetSpeedInfoUseCase

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class DashboardPresenterImpl<T: DashboardPresenter.DashboardView>(private val getCurrentSpeedUseCase: GetCurrentSpeedUseCase,
                                                                  private val getSpeedInfoModelUseCase: GetSpeedInfoUseCase,
                                                                  private val setSpeedInfoUseCase: SetSpeedInfoUseCase): DashboardPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    override fun subscribeToCurrentLocation() {
        getCurrentSpeedUseCase.executeObservable(object : DefaultSubscriber<SpeedInfoModel>(){
            override fun onCompleted() {
                Log.d("onxGetSpeed", "Completed.")
            }

            override fun onError(e: Throwable) {
                Log.d("onxGetSpeed", "Err: $e")
            }

            override fun onNext(value: SpeedInfoModel) {
                view?.showSpeed(value)
            }
        })
    }

    override fun getSpeedInfoModel() {
        getSpeedInfoModelUseCase.executeSingle(
                {view?.showSpeedInfo(it)},
                {Log.d("onxGetSpeedInfoModel", "Err:$it")}
        )
    }

//    override fun setSpeedModelInfo(speedInfoModel: SpeedInfoModel) {
//        setSpeedInfoUseCase.speedInfoModel = speedInfoModel
//        setSpeedInfoUseCase.executeCompletable(
//                {Log.d("onxSetSpeedInfoModel", "Completed.")},
//                {Log.d("onxSetSpeedInfoModel", "Err:$it")}
//        )
//    }
}