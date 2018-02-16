package com.example.speedtest.presentation.settings

import android.util.Log
import com.example.speedtest.domain.clear.ClearDatabaseUseCase
import com.example.speedtest.domain.location.ChangeProviderUseCase

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SettingsPresenterImpl<T: SettingsPresenter.SettingsView>(private val clearDatabaseUseCase: ClearDatabaseUseCase,
                                                               private val changeProviderUseCase: ChangeProviderUseCase): SettingsPresenter<T> {

    private var view: T? = null

    override fun clearDatabase() {
        clearDatabaseUseCase.executeCompletable(
                { Log.d("onxClearDatabase", "Completed.") },
                { Log.d("onxClearDatabase", "Err: $it") }
        )
    }

    override fun changeProvider() {
        changeProviderUseCase.executeCompletable(
                { Log.d("onxChangeProvider", "Completed.") },
                { Log.d("onxChangeProvider", "Err: $it") }
        )
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}