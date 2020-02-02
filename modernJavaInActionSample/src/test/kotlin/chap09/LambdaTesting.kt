package chap09

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LambdaTesting {

    @Test
    @Throws(Exception::class)
    fun testMoveRightBy() {
        val p1 = Point(10, 10)
        val p2 = Point(15, 10)
        assertEquals(p1.moveRightBy(5), p2)
    }

    @Test
    @Throws(Exception::class)
    fun testComparingByXAndThenY() {
        val p1 = Point(10, 10)
        val p2 = Point(10, 15)

        val result = Point.compareByXAndThenY.compare(p1, p2)
        assertTrue(result < 0)
    }

    @Test
    fun filterTest() {
        val numbers = listOf(1, 2, 3, 4)
        val even = filter(numbers) { i -> i % 2 == 0 }
        val biggerThanTwo = filter(numbers) { i -> i > 2 }
        assertEquals(even, listOf(2, 4))
        assertEquals(biggerThanTwo, listOf(3, 4))
    }
}