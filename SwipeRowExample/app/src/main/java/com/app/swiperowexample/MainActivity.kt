package com.app.swiperowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.swiperowexample.ui.SwipeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter = SwipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        table.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
        table.adapter = adapter

        adapter.usersList = users
    }
}