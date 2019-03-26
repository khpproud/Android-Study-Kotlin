package four

// Car2 클래스의 인스턴스와 컴패니언 객체가 생성되는 시점 비교
class Car2 {
    init {
        count++
        println("Car2 created")
    }

    companion object {
        var count: Int = 0
        init {
            // 자바와 비교하면 정적 초기화 블록에 해당
            println("Car2 companion object created")
        }
    }
}

fun main() {
    // 컴패니언 객체에 정의된 count 속성에 접근하면 객체 생성이 트리거 되지만 Car2 클래스의 인스턴스는 생성되지 않음
    //Car2.count

    // 컴패니언 객체에 접근하기 전에 Car 클래스의 인스턴스 생성
    Car2()
    Car2()
    Car2.count
}