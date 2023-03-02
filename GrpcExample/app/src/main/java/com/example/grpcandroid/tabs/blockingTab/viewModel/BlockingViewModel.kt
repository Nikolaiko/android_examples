package com.example.grpcandroid.tabs.blockingTab.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grpcandroid.service.GrpcService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlockingViewModel @Inject constructor(
    private val grpcService: GrpcService
): ViewModel() {
    private val nameValue = MutableLiveData("")
    val nameLiveData: LiveData<String> = nameValue

    private val responseValue = MutableLiveData("")
    val responseLiveData: LiveData<String> = responseValue

    fun sendHello() {
        val message = grpcService.sendBlockingHello(nameLiveData.value ?: "")
        responseValue.postValue(message)
    }

    fun sendServerStreamRequest() {
        val message = grpcService.sendServerStream(nameLiveData.value ?: "")
        responseValue.postValue(message)
    }

    fun updateName(newName: String) {
        nameValue.postValue(newName)
    }
}