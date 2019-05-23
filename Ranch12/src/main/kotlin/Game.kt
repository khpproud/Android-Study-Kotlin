import java.lang.IllegalStateException
import kotlin.text.StringBuilder

fun main() {
    Game.play()
}

object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("방문을 환영합니다.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())

            // 플레이어의 상태 출력
            printPlayerStatus(player)

            print("> 명령을 입력 하세요: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun showMap(): String {
        val current = player.currentPosition
        println(current)
        val sb: StringBuilder = StringBuilder()
        for (y in worldMap.indices) {
            for (x in worldMap[y].indices) {
                if (x == player.currentPosition.x && y == player.currentPosition.y) {
                    sb.append(String.format("%2c",'X'))
                } else {
                    sb.append(String.format("%2c",'O'))
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    private fun ringBell(): String {
        return if (currentRoom is TownSquare) {
            (currentRoom as TownSquare).ringBell()
        } else {
            "광장이 아니므로 종을 칠 수 없습니다!"
        }
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction 쪽 방향이 범위를 벗어남!")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, $direction 방향의 ${newRoom.name}(으)로 이동했습니다."
        } catch (e: Exception) {
            "잘못된 방향임: $directionInput"
        }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) {""}

        private fun commandNotFound() = "적합하지 않은 명령입니다!"

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "map" -> showMap()
            "ring" -> ringBell()
            "quit", "exit" -> System.exit(0)
            else -> commandNotFound()
        }
    }
}

// 객체 표현식(Object expression)
val abbandonedTownSquare = object : TownSquare() {
    override fun load() = "환영 받을 것을 예상했지만 여기는 아무도 없군요"
}