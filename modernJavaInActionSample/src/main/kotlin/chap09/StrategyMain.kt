package chap09

object StrategyMain {
    @JvmStatic
    fun main(args: Array<String>) {
        // Old school
        val v1: Validator = Validator(IsNumeric)
        println(v1.validate("aaaa"))
        val v2 = Validator(IsAllLowerCase)
        println(v2.validate("bbbb"))

        // With anonymous function
        val v3: Validator = Validator(
            object : ValidationStrategy {
                override fun execute(s: String): Boolean {
                    return s.matches(Regex("\\d+"))
                }
            }
        )
        println(v3.validate("1234"))

        // With lambda
        val v4 = ValidatorLambda { s -> s.matches(Regex("[a-z]+")) }
        println(v4.validate("abcd"))

    }

    @FunctionalInterface
    interface ValidationStrategy {
        fun execute(s: String): Boolean
    }

    private object IsAllLowerCase : ValidationStrategy {
        override fun execute(s: String): Boolean {
            return s.matches(Regex("[a-z]+"))
        }
    }

    private object IsNumeric : ValidationStrategy {
        override fun execute(s: String): Boolean {
            return s.matches(Regex("\\d+"))
        }
    }

    private class Validator(val strategy: ValidationStrategy) {
        fun validate(s: String) = strategy.execute(s)
    }

    private class ValidatorLambda(val strategy: (String) -> Boolean) {
        fun validate(s: String) = strategy.invoke(s)
    }
}