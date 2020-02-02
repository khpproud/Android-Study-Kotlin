package chap19

fun main() {
    val tj1 = TrainsJourney(40, TrainsJourney(30, null))
    val tj2 = TrainsJourney(20, TrainsJourney(50, null))

    val appended = append(tj1, tj2)
    visit(appended) { tj -> print("${tj?.price} - ") }
    println()

    val appended2 = append(tj1, tj2)
    visit(appended2) { tj -> print("${tj?.price} - ") }
    println()

    val linked = link(tj1, tj2)
    visit(linked) { tj -> print("${tj?.price} - ") }
    println()

    // recursions...
    val linked2 = link(tj1, tj2)
    visit(linked2) { tj -> print("${tj?.price} - ") }
    println()

    println("------------------------------")
    compareLinkAndAppend()
}

private fun compareLinkAndAppend() {
    println("Destructive update:")
    var firstJourney = TrainsJourney(1, null)
    var secondJourney = TrainsJourney(2, null)
    var xToz = link(firstJourney, secondJourney)
    println("firstJourney (X to Y) = $firstJourney")
    println("secondJourney (Y to Z) = $secondJourney")
    println("X to Z = $xToz")

    println("\nThe functional way:")
    firstJourney = TrainsJourney(1, null)
    secondJourney = TrainsJourney(2, null)
    xToz = append(firstJourney, secondJourney)
    println("firstJourney (X to Y) = $firstJourney")
    println("secondJourney (Y to Z) = $secondJourney")
    println("X to Z = $xToz")
}

class TrainsJourney(val price: Int, var onWard: TrainsJourney?) {

    override fun toString(): String {
        return String.format("TrainJourney[%d] -> %s", price, onWard)
    }
}

private fun link(a: TrainsJourney?, b: TrainsJourney?): TrainsJourney? {
    a ?: return b

    var t = a
    while (t?.onWard != null) {
        t = t.onWard
    }
    t?.onWard = b
    return a
}

private fun append(a: TrainsJourney?, b: TrainsJourney?): TrainsJourney? {
    return if (a == null) b else TrainsJourney(a.price, append(a.onWard, b))
}

private fun visit(journey: TrainsJourney?, consumer: (TrainsJourney?) -> Unit) {
    journey?.run {
        consumer.invoke(journey)
        visit(journey.onWard, consumer)
    }
}