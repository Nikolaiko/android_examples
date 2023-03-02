package com.example.grpcandroid.tabs.nonBlockingTab.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grpc_app_demo.HelloRequest
import com.example.grpcandroid.model.GrpcStreamListener
import com.example.grpcandroid.service.GrpcService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.stub.StreamObserver
import javax.inject.Inject

@HiltViewModel
class NonBlockingViewModel @Inject constructor(
    private val service: GrpcService
) : ViewModel(), GrpcStreamListener<String> {

    private val dataList = listOf("Nikolai", "Anna S.", "Anna K.", "Darya", "Rodion")
    val listLiveData: LiveData<List<String>> = MutableLiveData(dataList)

    private val responseData = MutableLiveData("")
    val responseLiveData: LiveData<String> = responseData

    private val streamResponseData = MutableLiveData("")
    val streamLiveData: LiveData<String> = streamResponseData

    private var listenerSubscription: StreamObserver<HelloRequest>? = null

    fun sendClientStream() {
        responseData.postValue("Sending...")
        streamResponseData.postValue("")

        listenerSubscription = service.sendClientStream(this)
        for(name in dataList) {
            listenerSubscription?.onNext(
                HelloRequest.newBuilder().setName(name).build()
            )
        }
    }

    fun sendBidirectional() {
        responseData.postValue("Sending...")
        streamResponseData.postValue("")

        listenerSubscription = service.sendBidirectionalStream(this)
        for(name in dataList) {
            listenerSubscription?.onNext(
                HelloRequest.newBuilder().setName(name).build()
            )
        }
    }

    fun completeClientStream() {
        listenerSubscription?.onCompleted()
    }

    override fun onNext(response: String) {
        var currentData = streamResponseData.value ?: ""
        currentData += ", $response"
        streamResponseData.postValue(currentData)
    }

    override fun onError(t: Throwable) {
        responseData.postValue("Error: ${t.message}")
    }

    override fun onCompleted() {
        responseData.postValue("Completed")
    }
}