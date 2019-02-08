package com.patrick.bmicalcurator

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이전에 입력한 값을 가져옴
        loadData()

        resultButton.setOnClickListener {
//            val intent = Intent(this, ResultActivity::class.java)
//            startActivity(intent)
            val weight = weightEditText.text.toString()
            val height = heightEditText.text.toString()

            //마지막에 입력한 내용 저장
            saveData(height.toFloat(), weight.toFloat())

            //Anko 라이브러리 사용
            startActivity<ResultActivity>(
                "weight" to weight,
                "height" to height
            )
        }
    }

    private fun saveData(height: Float, weight: Float) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0.0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0.0f)

        if(height != 0.0f && weight != 0.0f) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}
