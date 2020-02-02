package appC

import appC.StreamForker.ForkingStreamConsumer.Companion.END_OF_STREAM
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Stream
import java.util.stream.StreamSupport

class StreamForker<T>(
    private val stream: Stream<T>,
    private val forks: MutableMap<Any, Function<Stream<T>, *>> = hashMapOf()
) {

    fun fork(key: Any, f: Function<Stream<T>, *>): StreamForker<T> {
        forks[key] = f
        return this
    }

    fun getResults(): Results {
        val consumer: ForkingStreamConsumer<T> = build()
        try {
            stream.sequential().forEach(consumer)
        } finally {
            consumer.finish()
        }
        return consumer
    }

    private fun build(): ForkingStreamConsumer<T> {
        val queues: MutableList<BlockingQueue<T>> = mutableListOf()
        val actions: MutableMap<Any, Future<*>> =
            forks.entries.stream().reduce(hashMapOf(),
                { map, e ->
                    map[e.key] = getOperationResult(queues, e.value)
                    map
                },
                { m1, m2 ->
                    m1.putAll(m2)
                    m1
                })
        return ForkingStreamConsumer(queues, actions)
    }

    private fun getOperationResult(queues: MutableList<BlockingQueue<T>>, f: Function<Stream<T>, *>): Future<*> {
        val queue: BlockingQueue<T> = LinkedBlockingQueue()
        queues.add(queue)
        val spliterator: Spliterator<T> = BlockingQueueSpliterator(queue)
        val source: Stream<T> = StreamSupport.stream(spliterator, false)
        return CompletableFuture.supplyAsync { f.apply(source) }
    }

    @FunctionalInterface
    interface Results {
        fun <R> get(key: Any): R
    }

    @Suppress("UNCHECKED_CAST")
    private class ForkingStreamConsumer<T>(
        val queues: MutableList<BlockingQueue<T>>,
        val actions: MutableMap<Any, Future<*>>
    ) : Consumer<T>, Results {

        companion object {
            val END_OF_STREAM = Any()
        }

        override fun accept(t: T) = queues.forEach { it.add(t) }

        override fun <R> get(key: Any): R {
            try {
                return actions[key]?.get() as R
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun finish() = accept(END_OF_STREAM as T)
    }

    private class BlockingQueueSpliterator<T>(val q: BlockingQueue<T>) : Spliterator<T> {
        override fun estimateSize(): Long = 0

        override fun characteristics() = 0

        override fun tryAdvance(action: Consumer<in T>): Boolean {
            var t: T
            while (true) {
                try {
                    t = q.take()
                    break
                } catch (e: InterruptedException) {
                }
            }

            if (t !== END_OF_STREAM) {
                action.accept(t)
                return true
            }

            return false
        }

        override fun trySplit(): Spliterator<T>? = null
    }
}