package seven.library

// 코틀린 표준 라이브러리 함수 사용 예

fun main() {
    val mutableList = mutableListOf(1)
    // let
    val letResult = mutableList.let {
        it.add(2)
        listOf("A", "B", "C")
    }
    println(letResult)

    // apply
    val applyResult = mutableList.apply {
        this.add(3)
        listOf("A", "B", "C")
    }
    println(applyResult)

    // also
    val alsoResult = mutableList.also {
        it.add(4)
        listOf("A", "B", "C")
    }
    println(alsoResult)

    // run
    val runResult = mutableList.run {
        this.add(5)
        listOf("A", "B", "C")
    }
    println(runResult)

    // with
    val withResult = with(mutableList) {
        this.add(6)
        listOf("A", "B", "C")
    }
    println(withResult)
    println(mutableList)
}