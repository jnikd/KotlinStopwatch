package com.example.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.stopwatch.R.id.time
import com.example.stopwatch.R.id.timeText

class MainActivity : AppCompatActivity() {

    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeText = findViewById(R.id.timeText) as TextView

        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                timeText.text = timeValue.toString()
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
}
