package com.nikolai.mvpexample.moxyMVP

import com.nikolai.mvpexample.model.SomeActions
import moxy.MvpPresenter

class MoxyPresenter: MvpPresenter<MyMoxyView>() {
    private val repository = SomeActions(::actionCallback)

    init {
        println("PREASENTER CREATED")
    }

    fun startAction() {
        viewState.startAction()
        repository.someAction()
    }

    private fun actionCallback() {
        viewState.stopAction()
    }
}