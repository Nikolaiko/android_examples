@file:OptIn(ExperimentalFoundationApi::class)

package com.example.grpcandroid.mainScreen.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.example.grpcandroid.model.BottomBarItem
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grpcandroid.mainScreen.view.navigation.TabsNavigation
import com.example.grpcandroid.mainScreen.view.subviews.MainPageBottomBar

@Composable
fun MainScreenView() {
    val currentTabVal = remember {
        MutableLiveData(BottomBarItem.BlockingTabItem.route)
    }

    val currentTab = currentTabVal.observeAsState()
    val tabsNavigator = rememberNavController()

    Scaffold(
        bottomBar = {
            MainPageBottomBar(
                currentTab.value ?: BottomBarItem.BlockingTabItem.route
            ) {
                tabsNavigator.navigate(it) {
                    tabsNavigator.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                    currentTabVal.postValue(it)
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(
                PaddingValues(0.dp, 0.dp, 0.dp, it.calculateBottomPadding())
            )
        ) {
            TabsNavigation(navController = tabsNavigator)
        }
    }
}