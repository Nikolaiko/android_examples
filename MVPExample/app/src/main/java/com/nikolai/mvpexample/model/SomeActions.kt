package com.nikolai.mvpexample.model

import android.os.Handler
import java.util.*

class SomeActions(
    private var callback: ((ResponseData)-> Unit)
) {

    fun someAction() {
        Handler().postDelayed({
            callback(
                ResponseData(
                    "Kiber",
                    "Guy",
                    Date()
                )
            )
        }, 2000)
    }
}