package eleven_two_one

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// enum 클래스에 값 대입
enum class Direction2(var no: Int, val str: String) {
    NORTH(0, "north"), SOUTH(1, "south"), WEST(2, "west"), EAST(3, "east")
}

fun main() {
    val direction: Direction = Direction.NORTH

    println("${direction.name} ... ${direction.ordinal}")

    val directions: Array<Direction> = Direction.values()
    directions.forEach {
        t -> println(t.name)
    }

    val direction1 = Direction.valueOf("WEST")
    println("${direction1.name} ... ${direction1.ordinal}")

    val direction2: Direction2 = Direction2.NORTH

    println("no : ${direction2.no}, ${direction2.str}")

    direction2.no = 10

    println("no : ${direction2.no}, ${direction2.str}")

}