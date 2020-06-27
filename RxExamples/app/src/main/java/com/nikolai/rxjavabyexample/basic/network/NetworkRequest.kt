package com.nikolai.rxjavabyexample.basic.network

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NetworkRequest {
    fun performRequest(listener: NetworkListener) {
        GlobalScope.launch {
            delay(1000L)
            listener.onSuccess("Success")
        }
    }
}