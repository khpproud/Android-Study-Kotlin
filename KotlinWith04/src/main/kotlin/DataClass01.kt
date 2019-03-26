package four

// 데이터 클래스 (코틀린에서 equals, hashCode, toString, copy 함수를 기본적으로 제공 함)
data class Product(var name: String, var price: Double)

val productA = Product("Spoon", 30.2)
val productB = Product("Spoon", 30.2)
val productC = Product("Fork", 10.5)
val productD = productA.copy()
val productE = productA.copy(price = 24.0)
val productF = productA.copy(price = 21.0, name = "Knife")
fun main() {
    println(productA == productB)
    println(productA == productA)
    println(productA === productB)
    println(productA == productC)
    println(productB == productC)
    println(productB === productC)
    println(productB === productB)
    println(productA)
    println(productD)
    println(productE)
    println(productF)
}