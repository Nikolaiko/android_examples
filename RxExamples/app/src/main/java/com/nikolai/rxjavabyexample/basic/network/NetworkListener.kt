package com.nikolai.rxjavabyexample.basic.network

interface NetworkListener {
    fun onSuccess(result: String)
    fun onFail(message: String)
}