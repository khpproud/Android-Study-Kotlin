package thirteen_one_three

// 객체의 멤버 접근
class User {
    var name: String? = null
    var age: Int? = null

    fun sayHello() {
        println("Hello $name")
    }
    fun sayInfo() {
        println("i am $name, $age years old")
    }
}

// 객체명으로 선언해서 이용하는 예
class User2() {
    var name: String? = null
    var age: Int? = null

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }
}

fun letTestFun(user: User2) {
    println("letTestFun() : ${user.name} .. ${user.age}")
}

fun main() {
    // run() 함수 이용
    val result = run {
        println("lambdas function call...")
        10 + 20
    }
    println("result : $result")

    val user = User()
    user.name = "Park"
    user.age = 23
    user.sayHello()
    user.sayInfo()

    // 객체에서의 run() 함수 이용
    val runResult = user.run {
        name = "Park"
        age = 27
        sayHello()
        sayInfo()
        10 + 20
    }
    println("run result : $runResult")

    // apply() 함수 이용
    val user3 = user.apply {
        name = "Kim"
        sayHello()
        sayInfo()
    }
    println("user name : ${user.name}, user3 name : ${user3.name}")
    user.name = "AAA"
    user3.name = "BBB"
    println("user name : ${user.name}, user3 name : ${user3.name}")

    // let() 함수 이용
    val user4 = User2("Park", 33)
    letTestFun(user4)

    User2("Kim", 28).let {
        letTestFun(it)
    }

    // with() 함수 이용
    user.run {
        name = "Lee"
        sayHello()
    }

    with(user) {
        name = "Chin"
        sayHello()
    }
}

