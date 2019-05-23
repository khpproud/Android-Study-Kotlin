data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    // 플레이어의 이동에 따라 좌표가 변경된 Coordinate 인스턴스 반환
    fun updateCoordinate(playerCoordinate: Coordinate) =
            coordinate + playerCoordinate

}

fun getDirectionName(direction: Direction) =
        when (direction) {
            Direction.NORTH -> "북"
            Direction.EAST -> "동"
            Direction.SOUTH -> "남"
            Direction.WEST -> "서"
        }