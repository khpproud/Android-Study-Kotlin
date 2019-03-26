package four

// 클래스와 생성자의 가시성 정의 예
internal class Fruit2 private constructor() {
    var weight: Double? = null

    companion object {
        fun create() = Fruit2()
    }
}

fun main() {
    var fruit: Fruit2? = null                   // 접근 가능
    //fruit = Fruit2()                            // private 생성자 가시성이므로 에러
    fruit = Fruit2.create()                     // 가시성 한정자 없으모로(public) 인스턴스 생성 가능
}