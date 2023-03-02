package com.example.grpcandroid.service

import com.example.grpc_app_demo.GreeterGrpc
import com.example.grpc_app_demo.HelloRequest
import com.example.grpc_app_demo.HelloResponse
import com.example.grpcandroid.model.GrpcStreamListener
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver

class GrpcService {
    companion object {
        const val port = 50051
        const val address = "10.0.2.2"
    }

    private val channel = ManagedChannelBuilder.forAddress(
        address,
        port
    ).usePlaintext().build()

    private val blockingStub = GreeterGrpc.newBlockingStub(channel)
    private val nonBlockingStub = GreeterGrpc.newStub(channel)
    private var serverStreamObserver: GrpcStreamListener<String>? = null

    fun sendBlockingHello(name: String): String =
        blockingStub.sayHello(HelloRequest.newBuilder().setName(name).build()).message

    fun sendServerStream(name: String): String {
        val request = HelloRequest.newBuilder().setName(name).build()
        val replies = blockingStub.lotsOfReplies(request)
        return replies.asSequence().toList().map { it -> it.message }.reduce { acc, s -> "$acc, $s" }
    }

    fun sendClientStream(listener: GrpcStreamListener<String>): StreamObserver<HelloRequest> {
        serverStreamObserver = listener

        return nonBlockingStub.lotsOfRequests(object : StreamObserver<HelloResponse> {
            override fun onNext(response: HelloResponse) {
                serverStreamObserver?.onNext(response.message)
            }

            override fun onError(t: Throwable) {
                serverStreamObserver?.onError(t)
            }

            override fun onCompleted() {
                serverStreamObserver?.onCompleted()
            }
        })
    }

    fun sendBidirectionalStream(listener: GrpcStreamListener<String>): StreamObserver<HelloRequest> {
        serverStreamObserver = listener

        return nonBlockingStub.bidirectionalHello(object : StreamObserver<HelloResponse> {
            override fun onNext(response: HelloResponse) {
                serverStreamObserver?.onNext(response.message)
            }

            override fun onError(t: Throwable) {
                serverStreamObserver?.onError(t)
            }

            override fun onCompleted() {
                serverStreamObserver?.onCompleted()
            }
        })
    }
}