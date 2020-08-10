package com.app.swiperowexample

import com.app.swiperowexample.data.User

val users = listOf(
    User("First", "User"),
    User("Second", "User"),
    User("Last", "User")
)

typealias DeleteCallback = (Int)->Unit