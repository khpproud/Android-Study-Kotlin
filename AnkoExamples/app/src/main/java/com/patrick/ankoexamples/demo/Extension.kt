package com.patrick.ankoexamples.demo

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.ImageView
import com.bumptech.glide.Glide

// 확장함수로 프레임워크 확장하기
class Student(val age: Int)

fun main() {
    val studentA = Student(25)
    println(studentA.isAgeGreaterThan20())

    Student2.sayHi()
}

private fun Student.isAgeGreaterThan20(): Boolean = this.age > 20

// ImageView에 image를 세팅
private fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}

// 위위 코드를 디컴파일 하면
/**
 * private static final boolean isAgeGraterThan20(@NotNull Student $receiver) {
 *     return $receiver.getAge() > 20
 * }
 */

// 확장 기능을 속성으로 사용하기
val Context.preferences: SharedPreferences
get() = PreferenceManager.getDefaultSharedPreferences(this)

// 다음 처럼 사용
// context.preferences.getInt("...")

// companion object 를 이용해 정적인 방법으로 메소드에 접근
class Student2(val age: Int) {
    companion object {
    }
}

fun Student2.Companion.sayHi() {
    println("Hi")
}

