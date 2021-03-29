package com.nikolai.mvpexample.simpleMVP.passive

import com.nikolai.mvpexample.model.ResponseData
import com.nikolai.mvpexample.model.SomeActions

class SimpleMVPPresenter {
    private var view: SimpleMVPPassiveFragment? = null
    private val repository = SomeActions(::actionCallback)

    fun attach(presenterView: SimpleMVPPassiveFragment) {
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
        view?.setFirstName(data.name)
        view?.setLastName(data.lastName)
        view?.setDate(data.lastLogin.toString())
        view?.stopAction()
    }
}