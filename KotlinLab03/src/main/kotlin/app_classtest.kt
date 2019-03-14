// 매개변수 없는 주 생성자
class MyClass1 { }
class MyClass2() { }
class MyClass3 constructor() { }

// 생성자 매개변수 기본값 명시
class User3(name: String, age: Int = 0) { }

// 생성자 초기화 블록
class User4(name: String, age: Int) {
    init {
        println("This is init...")
    }
}

// 생성자 매개변수를 프로퍼티에 대입 이용
class User5(name: String, age: Int) {
    val name = name
    init {
        println("I am init... constructor argument : $name .. $age")
    }
    fun sayHello() {
        println("Hello $name")
    }
}

// 생성자 매새변수 선언 시 var, val 이용
class User6(private val name: String, private val age: Int) {
    init {
        println("i am init... constructor argument : $name .. $age")
    }

    fun sayHello() {
        println("Hello $name")
    }
}

// 매개 변수와 프로파티 동일 이름으로 선언
class User7(name: String, age: Int) {
    val name = "kim"
    init {
        println("i am init... constructor argument : $name")
    }
    fun sayHello() {
        println("Hello $name")
    }
}

// 주 생성자와 보조 생성자 동시 선언
class User8(name: String) {
    constructor(name: String, age: Int): this(name) { }
}

// 보조 생성자와 초기화 블록
class User9 {
    init {
        println("i am init block...")
    }
    constructor() {
        println("i am constructor...")
    }
}

// 보조 생성자와 주 생성자가 함게 선언된 경우
class User10(name: String) {
    init {
        println("init block... $name")
    }
    constructor(name: String, age: Int) : this(name) {
        println("constructor... $name ... $age")
    }
}

// 보조 생성자가 여러 개의 경우 생성자 연결
class User11(name: String) {
    constructor(name: String, age: Int): this(name) {

    }
    constructor(name: String, age: Int, email: String): this(name, age) {

    }
}

fun main() {
    val obj1 = MyClass1()
    val obj2 = MyClass2()
    val obj3 = MyClass3()
    val user4 = User3("Park", 10)
    val user5 = User3("Kim")    // 매개변수 기본 값 적용
    val user6 = User4("Kim", 10)
    val user7 = User5("Lee", 35)
    user7.sayHello()
    val user8 = User6("Choi", 22)
    user8.sayHello()
    val user9 = User7("Ha", 37)
    user9.sayHello()
    val user10 = User9()
    println("----- 주 생성자로 생성한 경우 -----")
    val user11 = User10("Park")
    println("----- 보조 생성자로 생성한 경우 -----")
    val user12 = User10("Park", 36)
    // 보조 생성자 여러 개 선언된 경우 객체 생성
    val user13 = User11("Song")
    val user14 = User11("Song", 24)
    val user15 = User11("Song", 24, "a@a.com")
}