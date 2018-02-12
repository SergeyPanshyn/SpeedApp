package com.example.speedtest.presentation

interface Presenter<T> {

    fun setView(view: T)

    fun destroy()
}