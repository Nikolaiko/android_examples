package com.nikolai.rxjavabyexample.basic.presenters

import com.nikolai.rxjavabyexample.basic.network.NetworkListener
import com.nikolai.rxjavabyexample.basic.network.NetworkRequest
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

class SourcePresenter {
    val requestResult: PublishSubject<String> = PublishSubject.create()

    fun successfulRequest() {
        val request = NetworkRequest()

        request.performRequest(object : NetworkListener {
            override fun onSuccess(result: String) {
                requestResult.onNext(result)
            }

            override fun onFail(message: String) {
                requestResult.onError(Exception(message))
            }
        })
    }
}