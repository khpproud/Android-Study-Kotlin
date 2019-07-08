package ch08_generic

// Comparator 를 사용해 제품 비교
data class Product(val name: String, val price: Double)

fun main() {
    val products = arrayOf(
        Product("Snow Ball", 870.0),
        Product("Smart Phone A", 999.0),
        Product("Drone", 240.0),
        Product("Mouse", 633.5),
        Product("Keyboard", 125.9),
        Product("Smart Phone B", 1500.9),
        Product("Mouse", 512.9)
    )

    products.sortWith(
        Comparator<Product> { p1, p2 ->
            when {
                p1.price > p2.price -> 1
                p1.price < p2.price -> -1
                else -> 0
            }
        }
    )

    products.forEach { println(it) }
    println()
    products.sortWith(compareBy({it.name}, {it.price}))         // compareBy 를 함께 사용해 2개의 정보 정렬
    products.forEach { println(it) }
}