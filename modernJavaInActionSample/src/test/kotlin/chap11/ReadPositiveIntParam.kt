package chap11

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class ReadPositiveIntParam {

    @Test
    fun testMap() {
        val props = Properties()
        props.setProperty("a", "5")
        props.setProperty("b", "true")
        props.setProperty("c", "-3")

        assertEquals(5, readDurationImperative(props, "a"))
        assertEquals(0, readDurationImperative(props, "b"))
        assertEquals(0, readDurationImperative(props, "c"))
        assertEquals(0, readDurationImperative(props, "d"))

        assertEquals(5, readDurationWithOptional(props, "a"))
        assertEquals(0, readDurationWithOptional(props, "b"))
        assertEquals(0, readDurationWithOptional(props, "c"))
        assertEquals(0, readDurationWithOptional(props, "d"))
    }

    private fun readDurationImperative(props: Properties, name: String): Int {
        val value = props.getProperty(name)
        if (value != null) {
            try {
                val i = value.toInt()
                if (i > 0) {
                    return i
                }
            } catch (nfe: NumberFormatException) { }
        }
        return 0
    }

    private fun readDurationWithOptional(props: Properties, name: String): Int {
        return Optional.ofNullable(props.getProperty(name))
            .flatMap(::s2i)
            .filter { i -> i > 0 }.orElse(0)
    }

    private fun s2i(s: String): Optional<Int> {
        return try {
            Optional.of(s.toInt())
        } catch (nfe: NumberFormatException) {
            Optional.empty()
        }
    }
}