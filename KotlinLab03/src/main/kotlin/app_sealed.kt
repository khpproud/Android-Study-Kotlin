package eleven_three_one
// sealed class 사용
sealed class Shape {
    class Circle(val radius: Double): Shape()
    class Rect(val width: Int, val height: Int): Shape()
}

// 추상 클래스와 Sealed 클래스의 차이
//sealed class SealedClass public constructor() {
//    public constructor(no: Int): this()
//
//    class SubOne(val radius: Double): SealedClass()
//    class SubTwo(val width: Int, val height: Int): SealedClass()
//}

abstract class AbstractClass public constructor() {
    public constructor(no: Int): this()
    class SubOne(val radius: Double): AbstractClass()
    class SubTwo(val width: Int, val height: Int): AbstractClass()
}


class Triangle(val bottom: Int, val height: Int): Shape()

fun main() {
    val shape1: Shape = Shape.Circle(10.0)

    val shape2: Shape = Triangle(10, 10)
}