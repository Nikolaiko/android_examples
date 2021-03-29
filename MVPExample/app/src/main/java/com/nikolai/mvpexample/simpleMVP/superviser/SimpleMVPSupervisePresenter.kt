package com.nikolai.mvpexample.simpleMVP.superviser

import com.nikolai.mvpexample.model.ResponseData
import com.nikolai.mvpexample.model.SomeActions

class SimpleMVPSupervisePresenter {
    private var view: SimpleMVPSuperviseFragment? = null
    private val repository = SomeActions(::actionCallback)

    fun attach(presenterView: SimpleMVPSuperviseFragment) {
        view = presenterView
    }

    fun detachView() {
        view = null
    }

    fun startAction() {
        view?.startAction()
        repository.someAction()
    }

    private fun actionCallback(data: ResponseData) {
        view?.updateView(data)
        view?.stopAction()
    }
}