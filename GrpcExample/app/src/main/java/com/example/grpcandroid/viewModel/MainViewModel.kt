package com.example.grpcandroid.viewModel

import androidx.lifecycle.ViewModel
import com.example.grpc_app_demo.GreeterGrpc
import com.example.grpc_app_demo.HelloRequest
import com.example.grpc_app_demo.HelloResponse
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MainViewModel: ViewModel() {
    companion object {
        const val port = 50051
        const val address = "10.0.2.2"
    }

    private val channel = ManagedChannelBuilder.forAddress(
        address,
        port
    ).usePlaintext().build()

    private val stub = GreeterGrpc.newBlockingStub(channel)


    fun getSayHello() {
        val reply = stub.sayHello(HelloRequest.newBuilder().setName("Nikolai").build())
    }

    fun sendMessageWithRequests() {
        try {
            val stub = GreeterGrpc.newStub(channel)
            var failed: Throwable? = null
            val finishLatch = CountDownLatch(1)
            val responseList = mutableListOf<HelloResponse>()
            val requestObserver = stub.lotsOfRequests(object : StreamObserver<HelloResponse> {
                override fun onNext(response: HelloResponse) {
                    responseList.add(response)
                }

                override fun onError(t: Throwable) {
                    failed = t
                    finishLatch.countDown()
                }

                override fun onCompleted() {
                    finishLatch.countDown()
                }
            })

            try {
                val requests = arrayOf(
                    HelloRequest.newBuilder().setName("TOM").build(),
                    HelloRequest.newBuilder().setName("ANDY").build(),
                    HelloRequest.newBuilder().setName("MANDY").build(),
                    HelloRequest.newBuilder().setName("John").build()
                )
                for (request in requests) {
                    requestObserver.onNext(request)
                }
            } catch (e: java.lang.RuntimeException) {
                requestObserver.onError(e)
            }

            requestObserver.onCompleted()

            println(responseList)
        } catch (e: Exception) {
            e
        }
    }

    fun sendMessageBiDirectional() {
        try {
            val stub = GreeterGrpc.newStub(channel)
            var failed: Throwable? = null
            val finishLatch = CountDownLatch(1)
            val responseList = mutableListOf<HelloResponse>()
            val requestObserver = stub.bidirectionalHello(object : StreamObserver<HelloResponse> {
                override fun onNext(response: HelloResponse) {
                    println("Response : $response")
                    responseList.add(response)
                }

                override fun onError(t: Throwable) {
                    failed = t
                    finishLatch.countDown()
                }

                override fun onCompleted() {
                    finishLatch.countDown()
                }
            })


            try {
                val requests = arrayOf(
                    HelloRequest.newBuilder().setName("TOM").build(),
                    HelloRequest.newBuilder().setName("ANDY").build(),
                    HelloRequest.newBuilder().setName("MANDY").build(),
                    HelloRequest.newBuilder().setName("John").build()
                )
                for (request in requests) {
                    requestObserver.onNext(request)
                }
            } catch (e: java.lang.RuntimeException) {
                requestObserver.onError(e)
            }

            requestObserver.onCompleted()


        } catch (e: Exception) {
            e
        }
    }
}