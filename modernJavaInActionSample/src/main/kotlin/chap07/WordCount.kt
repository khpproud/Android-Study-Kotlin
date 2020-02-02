package chap07

import java.util.*
import java.util.Spliterator.*
import java.util.function.Consumer
import java.util.stream.IntStream
import java.util.stream.Stream
import java.util.stream.StreamSupport

object WordCount {

    const val SENTENCE = (" Nel   mezzo del cammin  di nostra  vita "
            + "mi  ritrovai in una  selva oscura"
            + " che la  dritta via era   smarrita ")

    @JvmStatic
    fun main(args: Array<String>) {
        println("Found " + countWordsIteratively(SENTENCE) + " words")
        println("Found " + countWords(SENTENCE) + " words")
    }

    fun countWordsIteratively(s: String): Int {
        var counter: Int = 0
        var lastSpace = true
        for (c in s.toCharArray()) {
            lastSpace = if (c.isWhitespace()) {
                true
            } else {
                if (lastSpace) {
                    counter++
                }
                false
            }
        }
        return counter
    }

    fun countWords(s: String): Int {
//        val stream = IntStream.range(0, s.length).mapToObj(SENTENCE::get)
//        val stream = IntStream.range(0, s.length).parallel().mapToObj(SENTENCE::get)
        val spliterator: Spliterator<Char> = WordCounterSpliterator(s)
        val stream: Stream<Char> = StreamSupport.stream(spliterator, true)
        return countWords(stream)
    }

    private fun countWords(stream: Stream<Char>): Int {
        val wordCounter: WordCounter = stream.reduce(
            WordCounter(0, true),
            WordCounter::accumulate, WordCounter::combine
        )
        return wordCounter.getCounter()
    }

    class WordCounter(private var counter: Int, private var lastSpace: Boolean) {

        fun accumulate(c: Char): WordCounter {
            return if (c.isWhitespace()) {
                when (lastSpace) {
                    true -> this@WordCounter
                    false -> WordCounter(counter, true)
                }
            } else {
                when (lastSpace) {
                    true -> WordCounter(counter + 1, false)
                    false -> this@WordCounter
                }
            }
        }

        fun combine(wordCounter: WordCounter): WordCounter {
            return WordCounter(counter + wordCounter.counter, wordCounter.lastSpace)
        }

        fun getCounter() = counter
    }

    private class WordCounterSpliterator(val string: String, var currentChar: Int = 0) : Spliterator<Char> {

        override fun tryAdvance(action: Consumer<in Char>): Boolean {
            action.accept(string[currentChar++])
            return currentChar < string.length
        }

        override fun trySplit(): Spliterator<Char>? {
            val currentSize = string.length - currentChar
            if (currentSize < 10) {
                return null
            }
            for (splitPos in currentSize / 2 + currentChar until string.length) {
                if (string[splitPos].isWhitespace()) {
                    val spliterator: Spliterator<Char> =
                        WordCounterSpliterator(string.substring(currentChar, splitPos))
                    currentChar = splitPos
                    return spliterator
                }
            }
            return null
        }

        override fun estimateSize(): Long {
            return (string.length - currentChar).toLong()
        }

        override fun characteristics(): Int {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE
        }
    }
}