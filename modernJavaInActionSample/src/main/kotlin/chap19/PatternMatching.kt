package chap19

fun main() {
    simplify()

    val e: Expr = BinOp("+", Number(5), BinOp("*", Number(3), Number(4)))
    val result = evaluate(e)
    println("result = $result")
}

private fun simplify() {
    val binOpCase: (String, Expr, Expr) -> Expr = { opname, left, right ->
        when (opname) {
            "+" -> when {
                left is Number && left.value == 0 -> right
                right is Number && right.value == 0 -> left
                else -> BinOp(opname, left, right)
            }

            "*" -> when {
                left is Number && left.value == 1 -> right
                right is Number && right.value == 1 -> left
                else -> BinOp(opname, left, right)
            }
            else -> BinOp(opname, left, right)
        }
    }

    val numCase = { value: Int -> Number(value) }
    val defaultCase = { Number(0) }

    val e: Expr = BinOp("+", Number(5), Number(0))
    val match: Expr = patternMatchExpr(e, binOpCase, numCase, defaultCase)
    if (match is Number) {
        println("Number: $match")
    } else if (match is BinOp) {
        println("BinOp: $match")
    }
}

private fun evaluate(e: Expr): Int {
    val numCase = { value: Int -> value }
    val defaultCase = { 0 }
    val binOpCase: (String, Expr, Expr) -> Int = { opName, left, right ->
        when (opName) {
            "+" -> {
                if (left is Number && right is Number) left.value + right.value
                else if (right is Number && left is BinOp) right.value + evaluate(left)
                else if (left is Number && right is BinOp) left.value + evaluate(right)
                else if (left is BinOp && right is BinOp) evaluate(left) + evaluate(right)
                else defaultCase()
            }
            "*" -> {
                if (left is Number && right is Number) left.value * right.value
                else if (right is Number && left is BinOp) right.value * evaluate(left)
                else if (left is Number && right is BinOp) left.value * evaluate(right)
                else if (left is BinOp && right is BinOp) evaluate(left) * evaluate(right)
                else defaultCase()
            }
            else -> defaultCase()
        }
    }

    return patternMatchExpr(e, binOpCase, numCase, defaultCase)
}

sealed class Expr

data class Number(val value: Int) : Expr() {
    override fun toString(): String {
        return value.toString()
    }
}

data class BinOp(val opName: String, val left: Expr, val right: Expr) : Expr() {
    override fun toString(): String {
        return "($left $opName $right)"
    }
}

inline fun <T> myIf(b: Boolean, trueCase: () -> T, falseCase: () -> T): T {
    return if (b) trueCase() else falseCase()
}

@FunctionalInterface
interface TriFunctions<S, T, U, R> {
    fun apply(s: S, t: T, u: U): R
}

fun <T> patternMatchExpr(e: Expr,
                                /* binOpCase: TriFunctions<String, Expr, Expr, T>, */
                                binOpCase: (String, Expr, Expr) -> T,
                                numCase: (Int) -> T,
                                defaultCase: () -> T): T {
    return when (e) {
        is BinOp -> binOpCase.invoke(e.opName, e.left, e.right)
        is Number -> numCase.invoke(e.value)
        else -> defaultCase.invoke()
    }
}