package chap06

import java.util.*
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Collector.Characteristics.CONCURRENT

class ToListCollector<T> : Collector<T, MutableList<T>, List<T>> {

    override fun supplier(): Supplier<MutableList<T>> {
        return Supplier(::mutableListOf)
    }

    override fun accumulator(): BiConsumer<MutableList<T>, T> {
        return BiConsumer { list:MutableList<T>, item: T -> list.add(item) }
    }

    override fun finisher(): Function<MutableList<T>, List<T>> {
        return Function { mutableList: MutableList<T> -> mutableList.toList() }
    }

    override fun combiner(): BinaryOperator<MutableList<T>> {
        return BinaryOperator { list1, list2 ->
            list1.addAll(list2)
            list1
        }
    }

    override fun characteristics(): MutableSet<Collector.Characteristics> {
        return Collections.unmodifiableSet(EnumSet.of(CONCURRENT))
    }
}