package com.nikolai.mvpexample.moxyMVP

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MyMoxyView: MvpView {

    @AddToEndSingle
    fun startAction()

    @AddToEndSingle
    fun stopAction()
}