package chap08

import java.util.*
import java.util.Map
import java.util.Map.Entry.comparingByKey
import java.util.Map.entry
import java.util.stream.Collectors.toList
import kotlin.collections.ArrayList

object WorkingWithCollections {
    @JvmStatic
    fun main(args: Array<String>) {
        workingWithLists()
        workingWithMaps()
        computingOnMaps()
        removingFromMaps()
        replacingInMaps()
        mergingMaps()
    }

    private fun workingWithLists() {
        println("------ Working with Lists ------")

        println("---> Transforming list items with a Stream")
        var referenceCodes = Arrays.asList("a12", "C14", "b13")
        referenceCodes.stream()
            .map { code -> code[0].toUpperCase() + code.substring(1) }
            .collect(toList())
            .forEach(::println)
        println("... but the original List remains unchanged: $referenceCodes")

        println("---> Mutating a list with a ListIterator")

        val iterator = referenceCodes.listIterator()
        while (iterator.hasNext()) {
            val code = iterator.next()
            iterator.set(code[0].toUpperCase() + code.substring(1))
        }
        println("This time it's been changed: $referenceCodes")

        println("---> Mutating a list with replaceAll()")
        referenceCodes = Arrays.asList("a12", "C14", "b13")
        println("Back to the original: $referenceCodes")
        referenceCodes.replaceAll { code -> code[0].toUpperCase() + code.substring(1) }
        println("Changed by replaceAll(): $referenceCodes")
    }

    private fun workingWithMaps() {
        println("------ Working with Maps ------")

        println("---> Iterating a map with a for loop")
        val ageOfFriends = java.util.Map.of("Raphael", 30, "Olivia", 25,"Thibaut", 27)
        for (entry in ageOfFriends.entries) {
            val friend = entry.key
            val age = entry.value
            println("$friend is $age years old.")
        }

        println("---> Iterating a map with forEach()")
        ageOfFriends.forEach { friend, age -> println("$friend is $age years old.") }

        println("---> Iterating a map sorted by keys through a Stream")
        val favouriteMovies = java.util.Map.ofEntries(
            entry("Raphael", "Star Wars"),
            entry("Cristina", "Matrix"),
            entry("Olivia", "James Bond")
        )
        favouriteMovies.entries.stream()
            .sorted(comparingByKey())
            .forEachOrdered(::println)

        println("---> Using getOrDefault()")
        println(favouriteMovies.getOrDefault("Olivia", "Matrix"))
        println(favouriteMovies.getOrDefault("Thibaut", "Matrix"))
    }

    private fun computingOnMaps() {
        val friendsToMovies = HashMap<String, List<String>>()

        println("---> Adding a friend and movie in a verbose way")
        val friend = "Raphael"
        var movies = friendsToMovies.get(friend)?.toMutableList()
        if (movies == null) {
            movies = ArrayList()
            friendsToMovies.put(friend, movies)
        }
        movies.add("Star Wars")
        println(friendsToMovies)

        println("---> Adding a friend and movie using computeIfAbsent()")
        friendsToMovies.clear()
        friendsToMovies.computeIfAbsent("Raphael"
        ) { name -> arrayListOf("Star Wars") }
        println(friendsToMovies)
    }

    private fun removingFromMaps() {
        val favouriteMovies = HashMap<String, String>()
        favouriteMovies.put("Raphael", "Jack Reacher 2")
        favouriteMovies.put("Cristina", "Matrix")
        favouriteMovies.put("Olivia", "James Bond")
        val key = "Raphael"
        val value = "Jack Reacher 2"

        println("---> Removing an unwanted entry the cumbersome way")
        var result = remove(favouriteMovies, key, value)
        println("$favouriteMovies [$result]\n")

        favouriteMovies.put(key, value)

        println("---> Removing an unwanted the easy way")
        result = favouriteMovies.remove(key, value)
        println("$favouriteMovies [$result]")
    }

    private fun <K, V> remove(map: MutableMap<K, V>, key: K, value: V): Boolean {
        if (map.containsKey(key) && map.get(key) == value) {
            map.remove(key)
            return true
        }
        return false
    }

    private fun replacingInMaps() {
        val favouriteMovies = HashMap<String, String>()
        favouriteMovies["Raphael"] = "Star Wars"
        favouriteMovies["Olivia"] = "james bond"

        println("---> Replacing values in a amp with replaceAll()")
        favouriteMovies.replaceAll { _, movie -> movie.toUpperCase() }
        println(favouriteMovies)
    }

    private fun mergingMaps() {
        val family = Map.ofEntries(
            entry("Teo", "Star Wars"),
            entry("Cristina","James Bond")
        )
        val friends = Map.ofEntries(
            entry("Raphael", "Star Wars")
        )

        println("---> Merging the old way")
        val everyone = HashMap(family)
        everyone.putAll(friends)
        println(everyone)

        val friends2 = Map.ofEntries(
            entry("Raphael", "Star Wars"),
            entry("Cristina", "Matrix")
        )
        println("---> Merging maps using merge()")
        val everyone2 = HashMap(family)
        friends2.forEach { k, v -> everyone2.merge(k, v) { movie1, movie2 -> "$movie1&$movie2" } }
        println(everyone2)
    }
}