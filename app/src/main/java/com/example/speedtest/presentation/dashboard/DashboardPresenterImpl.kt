package com.example.speedtest.presentation.dashboard

import android.util.Log
import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.domain.chart.DeleteAllChartPointsUseCase
import com.example.speedtest.domain.speed.GetCurrentSpeedUseCase
import com.example.speedtest.domain.speed.GetSpeedInfoUseCase
import com.example.speedtest.domain.speed.SetSpeedInfoUseCase
import io.reactivex.observers.ResourceObserver

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class DashboardPresenterImpl<T: DashboardPresenter.DashboardView>(private val getCurrentSpeedUseCase: GetCurrentSpeedUseCase,
                                                                  private val getSpeedInfoModelUseCase: GetSpeedInfoUseCase,
                                                                  private val setSpeedInfoUseCase: SetSpeedInfoUseCase,
                                                                  private val deleteAllChartPointsUseCase: DeleteAllChartPointsUseCase): DashboardPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    override fun subscribeToCurrentLocation() {
        getCurrentSpeedUseCase.executeObservable(object : ResourceObserver<SpeedInfo>(){
            override fun onComplete() {
                Log.d("onxGetSpeed", "Completed")
            }

            override fun onError(e: Throwable) {
                Log.d("onxGetSpeed", "Err: $e")
            }

            override fun onNext(value: SpeedInfo) {
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

    override fun deleteAllChartPoints() {
        deleteAllChartPointsUseCase.executeCompletable(
                {},
                {}
        )
    }
}