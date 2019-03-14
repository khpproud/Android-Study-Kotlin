import kotlin.properties.Delegates

class User1 {
    // 사용자 정의 프로퍼티
    var greeting: String = "Hello"
    set(value) {
        field = "Hello $value"
    }
    get() = field.toUpperCase()

    var age: Int = 0
    set(value) {
        field = if (value > 0)
            value
        else
            0
    }
}

// 사용자 정의 프로퍼티 잘못 사용된 예
class User2 {
    val name: String = "Kim"
    get() = field.toUpperCase()
    //set(value) { field = "Hello $value"}

    // val은 초기값 생략 가능
    val age: Int
        get() = 10

    // var는 초기값 생략 불가능
    // var phone: String
    //    get() = "0100000"
}

// 주 생성자 프로퍼티 이용
class MyUser1(var name: String) {
    var myName: String = name
    get() = field.toUpperCase()
    set(value) {
        field = "Hello $value"
    }
}

// 주 생성자 프로퍼티 이용
class MyUser2(name: String) {
    var name: String = name
    get() = field.toUpperCase()
    set(value) {field = "Hello $value"}
}

// 초기화 블록에서 초기화
class MyUser3 {
    var data: String
    val data2 : Int

    init {
        data = "Park"
        data2 = 10
    }
}

// null 허용
class MyUser4 {
    val name1: String = "Park"
    var name2: String? = null
    val name3: String? = null
    var age: Int? = null

    constructor(name2: String, name3: String, age: Int) {
        this.name2 = name2
        // this.name3 = name3 // 에러
        this.age = age
    }
}

// 늦은 초기화 lateinit
class MyUser5 {
    lateinit var lateData: String
}

// lateinit 사용 에러
class MyUser6(/*lateinit var*/ data: String) {    // 주 생성자에 추가할 수 없음
    //lateinit val data2: String                  // val에는 선언할 수 없음
    //lateinit var data3: String?                 // null 허용에는 선언할 수 없음
    //lateinit var  data4: Int                    // 기초 타입에는 선언하 수 없음
    lateinit var data5: String                      // 가능

}

// lazy
val someData: String by lazy {
    println("i am someData lazy...")
    "hello"
}

class MyUser7 {
    val name: String by lazy {
        println("i am name lazy...")
        "Park"
    }
    val age: Int by lazy {
        println("i am age lazy...")
        17
    }

    init {
        println("i am init...")
    }

    constructor() {
        println("i am constructor...")
    }
}

class MyUser8 {
    var name: String by Delegates.observable("nonValue") { _, old, new ->
        println("old : $old ... new : $new")
    }
}

fun main() {
    val user1 = User1()
    user1.greeting = "Kim"
    println(user1.greeting)
    user1.age = -2
    println("age : ${user1.age}")

    val user2 = MyUser1("Park")
    user2.name = "lee"
    user2.myName = "kim"
    println("name : ${user2.name}")
    println("myName : ${user2.myName}")

    val user3 = MyUser2("Park")
    user3.name = "kim"
    println("name : ${user3.name}")

    val user4 = MyUser3()
    println("data : ${user4.data}")
    println("data2 : ${user4.data2}")

    // lateinit
    val user5 = MyUser5()
    user5.lateData = "Hello"
    println(user5.lateData)

    // lazy 초기화
    val user6 = MyUser7()
    println("name use before...")
    println("name : ${user6.name}")
    println("name use after...")
    println("age use before...")
    println("age : ${user6.age}")
    println("age use after...")

    val user7 = MyUser8()
    println(user7.name)
    user7.name = "Lee"
    user7.name = "Kim"
    println(user7.name)
}