package com.app.swiperowexample

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.swiperowexample.ui.SwipeAdapter
import com.app.swiperowexample.ui.SwipeCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter = SwipeAdapter()
    private val callBack = SwipeCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        table.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
        table.adapter = adapter

        adapter.usersList = users

        val helper = ItemTouchHelper(callBack)
        helper.attachToRecyclerView(table)

        table.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                callBack.onDraw(c)
            }
        })
    }
}