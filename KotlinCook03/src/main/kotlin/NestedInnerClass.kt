package chap03

// 중첩(Nested) 과 이너(Inner) 클래스 예
// 중첩 : 외부 클래스 객체에 대한 참조를 가지지 못함, 외부 클래스의 정적선언으로 멤버 접근 가능
class OutClNested {
    var a = 6
    fun printAB() {
        var b_ = InCl().b
        println("a = $a and b = $b_ from inside OutClNested")
    }

    class InCl {
        var b = "9"
        fun printB() {
            println("b = $b from inside InCl")
        }
    }
}

// 내부 : 외부 클래스 객체에 대한 참조를 가지며 외부클래스의 멤버인것 처럼 접근 가능
class OutClInner {
    var a = 6
    fun printAB() {
        var b_ = InCl().b
        println("a = $a and b = $b_ from inside OutCl")
    }

    inner class InCl {
        var b = "9"
        fun printAB() {
            println("a = $a and b = $b from inside InCl")
        }
    }
}

fun main() {
    var a = OutClNested()
    a.printAB()
    OutClNested.InCl().printB()

    var b = OutClInner()
    b.printAB()
    b.InCl().printAB()
}