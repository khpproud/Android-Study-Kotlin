package four

interface Player {
    fun play()
    fun stop()
}

fun playWith(player: Player) {
    print("I play with")
    player.play()
}

open class VideoPlayer {
    fun play() {
        println("Play Video")
    }
}

fun main() {
    // Player 인터페이스를 구현하는 익명 형식의 객체 생성(어댑터 패턴)
    val player = object : VideoPlayer(), Player {
        var duration: Double = 0.0

        override fun stop() {
            println("Stop Video")
        }
    }
    playWith(player)

    player.play()
    player.stop()
    player.duration = 12.5

    // 객체 식에 맞춤형 메소드와 속성을 정의 할 수 있음
    val data = object {
        var size = 1
        fun update() {
            println("blah~~~ $size")
        }
    }

    data.size = 2
    data.update()
}