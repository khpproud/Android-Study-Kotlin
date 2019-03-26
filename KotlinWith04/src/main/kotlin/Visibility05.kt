package four

// 게터와 세터의 가시성 예
class Car3 {
    init {
        count++
        println("Car created!!!")
    }

    companion object {
        init {
            println("Car companion object created!!!")
        }

        var count: Int = 0
        private set
    }
}