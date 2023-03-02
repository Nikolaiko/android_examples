package com.example.grpcandroid.tabs.nonBlockingTab.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.grpcandroid.model.VoidCallback

@Composable
fun NonBlockingTabView(
    startingData: LiveData<List<String>>,
    streamData: LiveData<String>,
    responseData: LiveData<String>,
    sendClientStreamCallback: VoidCallback,
    sendBidirectionalStreamCallback: VoidCallback,
    completeCallback: VoidCallback
) {
    val startingNames = startingData.observeAsState(listOf("Initial", "Values"))
    val response = responseData.observeAsState("")
    val stream = streamData.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Non Blocking message client stream test")
        Text(text = startingNames.value.reduce { acc, s -> "$acc,$s" })
        Button(onClick = sendClientStreamCallback) {
            Text(text = "Send client stream")
        }
        Button(onClick = sendBidirectionalStreamCallback) {
            Text(text = "Send bidirectional stream")
        }
        Button(onClick = completeCallback) {
            Text(text = "Complete")
        }
        Text(text = "Total result: ${response.value}")
        Text(text = "Stream : ${stream.value}")
    }
}