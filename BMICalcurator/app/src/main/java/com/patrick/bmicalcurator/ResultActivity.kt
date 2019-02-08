package com.patrick.bmicalcurator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //전달받은 키와 몸무게
        val height = intent.getStringExtra("height").toDouble();
        val weight = intent.getStringExtra("weight").toDouble();

        //BMI 계산 (체중 / 신장(m) ^ 2)
        val bmi = weight / Math.pow(height / 100.0, 2.0)

        //결과 표시
        when {
            bmi >= 35 -> resultTextView.text = getString(R.string.very_high_grade)
            bmi >= 30 -> resultTextView.text = getString(R.string.high_grade_2)
            bmi >= 25 -> resultTextView.text = getString(R.string.high_grade_1)
            bmi >= 23 -> resultTextView.text = getString(R.string.over_weight)
            bmi >= 18.5 -> resultTextView.text = getString(R.string.normal)
            else -> resultTextView.text = getString(R.string.low_weight)
        }

        //이미지 표시
        when {
            bmi >= 23 ->
                resultImageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_24dp)
            bmi >= 18.5 ->
                resultImageView.setImageResource(R.drawable.ic_sentiment_satisfied_24dp)
            else ->
                resultImageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_24dp)
        }

        toast("$bmi")
    }
}
