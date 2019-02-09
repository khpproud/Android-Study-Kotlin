package com.patrick.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            isRunning = !isRunning

            if(isRunning)
                start()
            else
                pause()
        }

        lapButton.setOnClickListener {
            recordLapTime()
        }

        resetFab.setOnClickListener {
            reset()
        }
    }

    //타이머 시작
    private fun start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)

        //0.01초 마다 증가시키며 UI 갱신
        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    //타이머 일시 정지
    private fun pause() {
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    //랩 타임 기록
    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        //맨 위에 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }

    //타이머 초기화
    private fun reset() {
        timerTask?.cancel()

        //모든 변수 초기화
        if(time != 0) {
            time = 0
            isRunning = false
            fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            secTextView.text = "0"
            milliTextView.text = "00"
        }

        //모든 랩타임 제거
        lapLayout.removeAllViews()
        lap = 1
    }
}
