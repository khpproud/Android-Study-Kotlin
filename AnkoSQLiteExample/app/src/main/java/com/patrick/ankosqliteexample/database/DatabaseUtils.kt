package com.patrick.ankosqliteexample.database

import android.content.Context

// Context에 확장 함수를 추가 함으로써 데이터베이스에 접근하는 방법 기본으로 제공
val Context.database: DatabaseHelper
get() = DatabaseHelper.getInstance(applicationContext)