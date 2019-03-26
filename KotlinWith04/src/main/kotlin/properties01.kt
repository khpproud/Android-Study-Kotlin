package four

class Car(var speed: Double)

// 맞춤형 게터, 세터
class Fruit(var weight: Double) {
    var ecoRating: Int = 3
    get() {
        println("getter value retrieved")
        return field
    }
    set(value) {
        field = if (value < 0) 0 else value
        println("setter new value assigned $field")
    }

    val heavy
    get() = weight > 10
}

fun main() {
    // 속성 접근 구문을 이용한 코틀린 스타일
    val car: Car = Car(7.4)
    car.speed = 9.2
    val speed = car.speed

    // 증가, 감소 연산자 함께 사용
    println(car.speed)
    car.speed++
    println(car.speed)
    car.speed--
    car.speed--
    println(car.speed)

    // 맞춤형 게터, 세터 테스트
    val fruit = Fruit(12.0)
    val ecoRating = fruit.ecoRating
    fruit.ecoRating = 3
    fruit.ecoRating = -1

    println(fruit.heavy)
    fruit.weight = 5.0
    println(fruit.heavy)

}