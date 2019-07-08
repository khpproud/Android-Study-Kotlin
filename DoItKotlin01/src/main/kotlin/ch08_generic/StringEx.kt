package ch08_generic

// 리터럴, 형식 문자 사용 예
fun main() {
    val text = """
        |Tell me and I forgot.
        |Tech me and I remember.
        |Involve me and I learn.
        |(Benjamin Franklin)
    """.trimMargin()                    // trim default 는 '|'
    println(text)

    val pi = 3.141592
    val dec = 10
    val s = "hello"
    println("pi = %.2f, %03d, %s".format(pi, dec, s))
}