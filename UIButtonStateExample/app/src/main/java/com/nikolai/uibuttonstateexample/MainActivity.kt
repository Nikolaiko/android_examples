package com.nikolai.uibuttonstateexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controlFirst.setOnClickListener {
            runOnUiThread {
                firstButton.isEnabled = !firstButton.isEnabled
            }
        }

        controlSecond.setOnClickListener {
            runOnUiThread {
                secondButton.isEnabled = !secondButton.isEnabled
            }
        }
    }
}
