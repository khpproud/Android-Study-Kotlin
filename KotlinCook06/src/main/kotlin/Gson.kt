package chap06

import com.google.gson.Gson

// Gson을 사용하여 JSON 응답구문 분석
// 더미 JSON 데이터
val jsonStr = """
    {
        "name" : "Donald Duck",
        "age" : 21,
        "isAwesome" : true
    }
""".trimIndent()

// 데이터를 저장할 데이터 클래스
data class Information(val name: String, val age: Int, val isAwesome: Boolean)

fun main() {
    val information: Information =
        Gson().fromJson(jsonStr, Information::class.java)

    println(information)
}