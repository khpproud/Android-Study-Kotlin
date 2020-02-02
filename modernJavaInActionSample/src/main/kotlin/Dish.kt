import java.util.*

data class Dish(val name: String,
                val vegetarian: Boolean,
                val calories: Int,
                val type: Type
) {

    override fun toString() = name

    enum class Type { MEAT, FISH, OTHER }

    enum class CaloricLevel { DIET, NORMAL, FAT }
}

val menu: List<Dish> = listOf(
    Dish("pork", false, 800, Dish.Type.MEAT),
    Dish("beef", false, 700, Dish.Type.MEAT),
    Dish("chicken", false, 400, Dish.Type.MEAT),
    Dish("french fries", true, 530, Dish.Type.OTHER),
    Dish("rice", true, 350, Dish.Type.OTHER),
    Dish("season fruit", true, 120, Dish.Type.OTHER),
    Dish("pizza", true, 550, Dish.Type.OTHER),
    Dish("prawns", false, 400, Dish.Type.FISH),
    Dish("salmon", false, 450, Dish.Type.FISH)
)

val dishTags: MutableMap<String, List<String>> = hashMapOf(
    "pork" to listOf("greasy", "salty"),
    "beef" to listOf("salty", "roasted"),
    "chicken" to listOf("fried", "crisp"),
    "french fries" to listOf("greasy", "fried"),
    "rice" to listOf("light", "natural"),
    "season fruit" to listOf("fresh", "natural"),
    "pizza" to listOf("tasty", "salty"),
    "prawns" to listOf("tasty", "roasted"),
    "salmon" to listOf("delicious", "fresh")
)