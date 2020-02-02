package chap17

import kotlin.random.Random

data class TempInfo (val town: String, val temp: Int) {

    companion object {
        private val random = Random(0)

        fun fetch(town: String): TempInfo {
            if (random.nextInt(10) == 0) {
                throw RuntimeException("Error!")
            }
            return TempInfo(town, random.nextInt(100))
        }
    }

    override fun toString(): String {
        return "$town:$temp"
    }
}