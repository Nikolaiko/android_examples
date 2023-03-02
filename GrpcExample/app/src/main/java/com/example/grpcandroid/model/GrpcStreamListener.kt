package com.example.grpcandroid.model

interface GrpcStreamListener<T> {
    fun onNext(response: T)
    fun onError(t: Throwable)
    fun onCompleted()
}