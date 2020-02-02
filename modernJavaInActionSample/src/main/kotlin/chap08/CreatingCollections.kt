package chap08

import java.util.*
import java.util.List
import java.util.Map
import java.util.Map.entry
import java.util.Set
import java.util.stream.Collectors.toSet
import java.util.stream.Stream

object CreatingCollections {
    @JvmStatic
    fun main(args: Array<String>) {
        creatingLists()
        creatingSets()
        creatingMaps()
    }

    private fun creatingLists() {
        println("------ Creating Lists ------")

        println("---> Creating a list the old-fashioned way")
        val friends: MutableList<String> = arrayListOf()
        friends.add("Raphael")
        friends.add("Olivia")
        friends.add("Thibaut")
        println(friends)

        println("---> Using Arrays.asList()")
        val friends2 = Arrays.asList("Raphael", "Olivia")
        friends2[0] = "Richard"
        println(friends2)
        try {
            friends2.add("Thibaut")
            println("We shouldn't get here...")
        } catch (e: UnsupportedOperationException) {
            println("As expected, we can't add items to a list created with Arrays.asList().")
        }

        println("---> Creating a Set from a List")
        val friends3 = HashSet(Arrays.asList("Raphael", "Olivia", "Thibaut"))
        println(friends3)

        println("---> Creating a Set from a Stream")
        val friends4 = Stream.of("Raphael", "Olivia", " Thibaut")
            .collect(toSet())
        println(friends4)

        println("---> Creating a List with List.of()")
        val friends5 = List.of("Raphael", " Olivia", "Thibaut")
        println(friends5)
        try {
            friends5.add("Chih-Chun")
            println("We shouldn't get here...")
        } catch (e: UnsupportedOperationException) {
            println("As expected, we can't add items to a list created with List.of().")
        }
        try {
            friends5[1] = "Chih-Chun"
            println("We shouldn't get here...")
        } catch (e: UnsupportedOperationException) {
            println("Neither can we replace items in such a list.")
        }
    }

    private fun creatingSets() {
        println("------ Creating Sets ------")

        println("---> Creating a Set with Set.of()")
        val friends = Set.of("Raphael", "Olivia", "Thibaut")
        println(friends)

        println("---> Trying to pass duplicate items to Set.of()")
        try {
            val friends2 = Set.of("Raphael", "Olivia", "Olivia")
            println("We shouldn't get here...")
        } catch (e: IllegalArgumentException) {
            println("As expected, duplicate items are not allowed with Set.of()")
        }

        val friends3 = setOf("Raphael", "Olivia", "Olivia")
        println(friends3)
        println("But Kotlin's setOf() contains duplicate elements by eliminating items ")
    }

    private fun creatingMaps() {
        println("---> Creating a Map with Map.of()")
        val ageOfFriends = Map.of("Raphael", 30, "Olivia", 23)
        println(ageOfFriends)

        println("---> Creating a Map with Map.ofEntries()")
        val ageOfFriends2 = Map.ofEntries(
            entry("Raphael", 30),
            entry("Olivia", 23)
        )
        println(ageOfFriends2)
    }

}