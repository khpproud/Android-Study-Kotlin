package ch09_collection

// 시퀀스를 이용한 소수
fun main() {
    val primes = generateSequence(2 to generateSequence(3) { it + 2 }) { it ->
        val currSeq = it.second.iterator()
        val nextPrime = currSeq.next()
        println("nextPrime : $nextPrime")
        nextPrime to currSeq.asSequence().filter {
            println("(it = $it, nextPrime = $nextPrime)")
            it % nextPrime != 0 }
    }.map { it.first }
    println(primes.take(10).toList())
}