package com.example.grpcandroid.tabs.blockingTab.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.grpcandroid.model.DataCallback
import com.example.grpcandroid.model.VoidCallback

@Composable
fun BlockingTabView(
    nameLiveData: LiveData<String>,
    responseLiveData: LiveData<String>,
    updateNameCallback: DataCallback<String>,
    sendCallback: VoidCallback,
    serverStreamCallback: VoidCallback
) {
    val name = nameLiveData.observeAsState("")
    val response = responseLiveData.observeAsState("Response")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Blocking message test")
        TextField(
            value = name.value,
            onValueChange = updateNameCallback
        )
        Button(onClick = sendCallback) {
            Text(text = "Send Hello")
        }
        Text(text = "Blocking server stream")
        Button(onClick = serverStreamCallback) {
            Text(text = "Send Request to server stream")
        }
        Text(text = response.value)
    }

}