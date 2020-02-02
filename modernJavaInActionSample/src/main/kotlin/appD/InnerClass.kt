package appD

import java.util.function.Function

class InnerClass {
    val f: Function<Any, String> = object : Function<Any, String> {
        override fun apply(t: Any): String {
            return t.toString()
        }
    }
}