package com.nikolai.mvpexample.simpleMVP

import com.nikolai.mvpexample.model.SomeActions

class SimpleMVPPresenter {
    private var view: SimpleMVPFragment? = null
    private val repository = SomeActions(::actionCallback)

    fun attach(presenterView: SimpleMVPFragment) {
        view = presenterView
    }

    fun detachView() {
        view = null
    }

    fun startAction() {
        view?.startAction()
        repository.someAction()
    }

    private fun actionCallback() {
        view?.stopAction()
    }
}