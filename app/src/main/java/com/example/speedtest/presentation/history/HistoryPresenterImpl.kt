package com.example.speedtest.presentation.history

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class HistoryPresenterImpl<T: HistoryPresenter.HistoryView>: HistoryPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}