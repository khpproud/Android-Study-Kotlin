package four

enum class Color {
    RED,
    ORANGE,
    BLUE,
    GRAY
}

fun main() {
    val favoriteColor = Color.BLUE

    // 문자열을 enum으로 바꿈
    var selectedColor = Color.valueOf("BLUE")
    println(selectedColor == Color.BLUE)

    // 코틀린 헬퍼메소드 enumValueOf 사용
    selectedColor = enumValueOf("GRAY")
    println(selectedColor == Color.GRAY)

    // 열거형의 모든 값을 표시
    for (color in Color.values()) {
        println("name : ${color.name}, ordinal : ${color.ordinal}")
    }

    // 코틀린 헬퍼메소드 enumValues 사용
    for (color in enumValues<Color>()) {
        println("name : ${color.name}, ordinal : ${color.ordinal}")
    }

}