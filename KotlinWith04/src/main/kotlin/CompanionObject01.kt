package four

// Companion 객체를 이용해 Car1 클래스의 인스턴스 수를 추적해보자
class Car1 {
    init {
        count++
    }

    companion object {
        var count: Int = 0
        private set         // 외부에서 count를 바꿀 수 없도록 private setter 정의
    }
}

fun main() {
    // 클래스는 컴패니언 객체에 정의된 메소드와 속성에 모두 접근할 수 있지만 컴패니언 객체는 클래스의 내용에 접근 할 수 없음
    println(Car1.count)
    Car1()
    Car1()
    println(Car1.count)

    // 컴패니언 객체는 컴패니언 클래스에 의해 생성되며 정적 속성으로 유지되는 싱글톤임
}