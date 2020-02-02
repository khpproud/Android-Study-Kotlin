package appC

import Dish
import menu
import java.util.function.Function
import java.util.stream.Collectors.*
import java.util.stream.Stream

fun main() {
    val menuStream: Stream<Dish> = menu.stream()

    val results: StreamForker.Results = StreamForker<Dish>(menuStream)
        .fork("shortMenu", Function<Stream<Dish>, String> { s -> s.map(Dish::name).collect(joining(", ")) })
        .fork("totalCalories", Function<Stream<Dish>, Int> { s -> s.mapToInt(Dish::calories).sum() })
        .fork("mostCaloricDish", Function<Stream<Dish>, Dish> { s -> s.collect(
            reducing { d1, d2 -> if (d1.calories > d2.calories) d1 else d2 } ).get() })
        .fork("dishedByType", Function<Stream<Dish>, Map<Dish.Type, List<Dish>>> { s -> s.collect(groupingBy(Dish::type)) })
        .getResults()

    val shortMenu = results.get<String>("shortMenu")
    val totalCalories = results.get<Int>("totalCalories")
    val mostCaloricDish = results.get<Dish>("mostCaloricDish")
    val dishedByType = results.get<Map<Dish.Type, List<Dish>>>("dishedByType")

    println("Short Menu: $shortMenu")
    println("Total calories: $totalCalories")
    println("Most caloric dish: $mostCaloricDish")
    println("Dishes by type: $dishedByType")
}