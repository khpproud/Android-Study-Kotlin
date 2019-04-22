package com.patrick.ankoexamples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*

class DummyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Anko에서 intent 사용하기
        startActivity(intentFor<DummyActivity>("data" to 5).singleTop())

        // 플래그 추가안한다면 더욱 단순
        startActivity<DummyActivity>("data" to 5)

        // 추가적인 데이터 전달에더 더 긴줄이 필요하지 않음
        startActivity<DummyActivity>("data" to 1, "another" to 2, "etc" to "blah...")

        // Anko를 이용해 전화걸기 - Manifest에 permission 추가해야됨 CALL_PHONE
        makeCall("+821011112222")

        // SMS 문자메시지 보내기
        sendSMS("+821011223344", "Say Ho~~~")

        // 웹페이지 연결
        browse("https://www.naver.com")

        // 텍스트 공유
        share("Hey", "Some Subject")
        share("Good")

        // email 보내기
        email("blah@goo.com", "Subject", "Text")


    }
}
