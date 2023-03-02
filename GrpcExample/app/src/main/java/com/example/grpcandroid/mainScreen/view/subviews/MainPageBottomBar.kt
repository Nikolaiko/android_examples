package com.example.grpcandroid.mainScreen.view.subviews

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.grpcandroid.R
import com.example.grpcandroid.model.BottomBarItem
import com.example.grpcandroid.model.DataCallback
import com.example.grpcandroid.ui.theme.Teal200

@Composable
fun MainPageBottomBar(
    currentRoute: String,
    onItemClick: DataCallback<String>? = null
) {
    val items = listOf(
        BottomBarItem.BlockingTabItem,
        BottomBarItem.NonBlockingTabItem,
    )

    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Gray,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = R.drawable.ic_launcher_background), contentDescription = item.route) },
                label = { Text(text = item.route) },
                selected = currentRoute == item.route,
                selectedContentColor = Teal200,
                alwaysShowLabel = false,
                onClick = { onItemClick?.invoke(item.route) }
            )
        }
    }
}