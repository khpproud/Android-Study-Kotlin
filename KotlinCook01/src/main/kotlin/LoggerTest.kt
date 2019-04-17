package chap01

class SomeClass {
    companion object { val log by logger() }
    fun doSomething() {
        log.info("Did something!!!")
    }
}

fun main() {
    val some = SomeClass()
    some.doSomething()
}