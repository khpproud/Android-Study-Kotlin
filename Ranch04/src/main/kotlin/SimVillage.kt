// 익명함수와 함수타입
fun main() {
    val numLetters = "Mississippi".count();
    val numSLetters = "Mississippi".count { letter -> letter == 's' }
    println(numLetters)
    println(numSLetters)

    val greetingFunction: () -> String = {
        val currentYear = 2019
        "SimVillage 방문을 환영, Master! (copyright $currentYear)"
    }
    println(greetingFunction())

    val greetingFunctionWithName: (String) -> String = { playerName ->
        val currentYear = 2019
        "SimVillage 방문을 환영, $playerName! (copyright $currentYear)"
    }
    println(greetingFunctionWithName("Lee"))

    // it 키워드 사용
    val greetingFunctionWithIt: (String) -> String = {
        val currentYear = 2019
        "SimVillage 방문을 환영, $it! (copyright $currentYear)"
    }
    println(greetingFunctionWithIt("Kim"))

    // Type Inference (타입 추론)
    val greetingFunctionWithTypeInference = { playerName: String, numBuildings: Int ->
        val currentYear = 2019
        println("$numBuildings 채의 건물이 추가됨")
        "SimVillage 방문을 환영, $playerName! (copyright $currentYear)"
    }
    println(greetingFunctionWithTypeInference("Park", 3))

    runSimulation("Ha", greetingFunctionWithTypeInference)

    // 단축 문법
    runSimulation("Jun") { playerName, numBuildings ->
        val currentYear = 2019
        println("$numBuildings 채의 건물이 추가됨")
        "SimVillage 방문을 환영, $playerName! (copyright $currentYear)"
    }

    runSimulationWithMR("Jang", ::printConstructionCost) { playerName, numBuildings ->
        val currentYear = 2019
        println("$numBuildings 채의 건물이 추가됨")
        "SimVillage 방문을 환영, $playerName! (copyright $currentYear)"
    }

    // 반환 타입으로 함수 사용하기 예
    runSimulation()
}

// 함수를 인자로 받는 함수(람다) 정의
inline fun runSimulation(playerName: String, greetingFunction: (String, Int) -> String) {
    val numBuildings = (1.. 3).shuffled().last()        // 1, 2, 3 중 무작위로 하나를 선택
    println(greetingFunction(playerName, numBuildings))
}

// 함수 참조(Function Reference) 예
fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("건축 비용 : ${cost * numBuildings}")
}

inline fun runSimulationWithMR(playerName: String, costPrinter: (Int) -> Unit,
                               greetingFunction: (String, Int) -> String) {
    val numBuildings = (1.. 3).shuffled().last()
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}

fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Ryu"))
    // 코트린의 람다는 클로저 : 익명함수의 외부에 선언된 변수의 참조 및 변경 가능
    println(greetingFunction("Ryu"))
}

fun configureGreetingFunction(): (String) -> String {
    val structureType = "Hospital"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2019
        numBuildings += 1
        println("$numBuildings 채의 $structureType 이 추가됨")
        "SimVillage 방문을 환영, $playerName! (copyright $currentYear)"
    }
}