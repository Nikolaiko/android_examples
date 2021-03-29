package com.nikolai.mvpexample.moxyMVP

import com.nikolai.mvpexample.model.ResponseData
import com.nikolai.mvpexample.model.SomeActions
import moxy.MvpPresenter

class MoxyPresenter: MvpPresenter<MyMoxyPassiveView>() {
    private val repository = SomeActions(::actionCallback)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun startAction() {
        viewState.startAction()
        repository.someAction()
    }

    private fun actionCallback(data: ResponseData) {
        viewState.stopAction()
    }
}