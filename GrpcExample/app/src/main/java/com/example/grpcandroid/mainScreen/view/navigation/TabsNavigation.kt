package com.example.grpcandroid.mainScreen.view.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.grpcandroid.model.MainScreenTabRoute
import com.example.grpcandroid.tabs.blockingTab.view.BlockingTabView
import com.example.grpcandroid.tabs.blockingTab.viewModel.BlockingViewModel
import com.example.grpcandroid.tabs.nonBlockingTab.view.NonBlockingTabView
import com.example.grpcandroid.tabs.nonBlockingTab.viewModel.NonBlockingViewModel

@ExperimentalFoundationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TabsNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = MainScreenTabRoute.BlockingTab.name) {
        composable(route = MainScreenTabRoute.BlockingTab.name) {
            val viewModel = hiltViewModel<BlockingViewModel>()
            BlockingTabView(
                nameLiveData = viewModel.nameLiveData,
                responseLiveData = viewModel.responseLiveData,
                updateNameCallback = viewModel::updateName,
                sendCallback = viewModel::sendHello,
                serverStreamCallback = viewModel::sendServerStreamRequest
            )
        }
        composable(route = MainScreenTabRoute.NonBlockingTab.name) {
            val viewModel = hiltViewModel<NonBlockingViewModel>()
            NonBlockingTabView(
                startingData = viewModel.listLiveData,
                streamData = viewModel.streamLiveData,
                responseData = viewModel.responseLiveData,
                sendClientStreamCallback = viewModel::sendClientStream,
                sendBidirectionalStreamCallback = viewModel::sendBidirectional,
                completeCallback = viewModel::completeClientStream
            )
        }
    }
}