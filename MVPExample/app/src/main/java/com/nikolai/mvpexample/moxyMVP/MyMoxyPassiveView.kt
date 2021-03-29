package com.nikolai.mvpexample.moxyMVP

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MyMoxyPassiveView: MvpView {

    @AddToEndSingle
    fun startAction()

    @AddToEndSingle
    fun stopAction()
}