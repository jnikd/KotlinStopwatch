package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEFAULT_TEXT = "00:00:00"
    }

    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeText = findViewById(R.id.timeText) as TextView
        val startButton = findViewById(R.id.start) as Button
        val stopButton = findViewById(R.id.stop) as Button
        val resetButton = findViewById(R.id.reset) as Button

        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                timeText.text = timeToText(timeValue)
                handler.postDelayed(this, 1000)
            }
        }

        startButton.setOnClickListener {
            handler.post(runnable)
        }

//        startButton.setOnClickListener {
//            object : View.OnClickListener {
//                override fun onClick(view: View) {
//                    handler.post(runnable)
//                }
//            }
//        }

        stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }

        resetButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            timeValue = 0
            timeText.text = timeToText()
        }
    }

    private fun timeToText(time: Int = 0): String {
        return if (time == 0) {
            DEFAULT_TEXT
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 3600
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}
