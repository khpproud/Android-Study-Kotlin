package com.patrick.ankonetworkexample

import android.content.Context

// SharedPreferences 사용 Helper 클래스
class Prefs(context: Context) {
    val sharedPreferences = context.getSharedPreferences("sample", Context.MODE_PRIVATE)
    // 사용자 이름을 저장할 용도
    val PREF_USERNAME = "pref_username"

    // 속성의 getter, setter 를 이용해 SharedPreferences로부터 값을 가져오고 저장하도록 함
    var username: String
    get() = sharedPreferences.getString(PREF_USERNAME, "")
    set(value) = sharedPreferences.edit().putString(PREF_USERNAME, value).apply()
}