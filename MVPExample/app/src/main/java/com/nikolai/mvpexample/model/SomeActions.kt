package com.nikolai.mvpexample.model

import android.os.Handler

class SomeActions(
    private var callback: (()-> Unit)
) {

    fun someAction() {
        Handler().postDelayed(callback, 2000)
    }
}