import java.io.File

/**
 * 코틀린의 표준 함수 apply, let, run, with, also, takIf 의 활용법 예
 * receiver object(수신자 객체): 확장 함수 형태로 제공됨
 */
fun main() {
    // apply - 객체의 구성 등에 사용 - 람다의 실행이 끝나면 현재의 수신자 객체를 반환
    val menuFile = File("menu-file.txt").apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }

    // let - 함수의 인자로 전달된 람다를 실행 후 결과를 반환 - 람다에 포함된 마지막줄의 실행 결과를 반환
    val firstItemSquared = listOf(1, 2, 3).first().let {
        it * it
    }

    // run - apply와 동일한 연관 범위를 제공하지만 수신자 객체를 반환하지 않음
    val servesDragonsBreath = menuFile.run {
        readText().contains("Dragon's Breath")
    }

    fun nameIsLong(name: String) = name.length >= 10
    println("David".run(::nameIsLong))
    println("PolarCubic, Supreme of Master".run(::nameIsLong))

    "PolarCubic, Supreme of Master".run(::nameIsLong)
        .run(::println)

    // with - run과 동일하지만 수신자 객체를 첫 번째 매개변수의 인자로 받음
    val nameToLongWith = with("PolarCubic, Supreme of master") {
        length >= 10
    }

    // also - let과 유사하지만 람다의 결과가 아닌 수신자 객체를 반환
    var fileContens: List<String>
    File("menu-file.txt").also {
        println(it.name)
    }.also {
        fileContens = it.readLines()
    }

    // takeIf - 조건식(Predicate)을 실행 후 true면 수신자 객체 반환, false면 null이 반환
    val fileContents2 = File("menu-file.txt")
        .takeIf { it.canRead() && it.canWrite() }
        ?.readText()

    // takeUnless - 조건식이 true가 아니면 원래의 값을 반환
    val fileContents3 = File("menu-file.txt")
        .takeUnless { it.isHidden }?.readText()
}