package chap12

import arrow.optics.optics
import arrow.optics.Lens
import arrow.syntax.function.curried

// Optics : 불변 데이터 구조를 우아하게 업데이트 하기 위한 추상화
// Lens : 구조에 초점을 맞출 수 있는 함수적 참조이며 타겟의 읽기, 쓰기, 수정이 가능
typealias GB = Int

@optics data class Memory(val size: GB) {
    companion object
}
@optics data class MotherBoard(val brand: String, val memory: Memory) {
    companion object
}
@optics data class Laptop(val price: Double, val motherBoard: MotherBoard) {
    companion object
}

// Lens 값 작성
val laptopPrice: Lens<Laptop, Double> = Lens(
    get = { laptop -> laptop.price },
    set = { laptop, price -> laptop.copy(price = price) })

val laptopMotherBoard: Lens<Laptop, MotherBoard> = Lens(
    get = { laptop -> laptop.motherBoard },
    set = { laptop, mb -> laptop.copy(motherBoard = mb) })

val motherBoardMemory: Lens<MotherBoard, Memory> = Lens(
    get = { mb -> mb.memory },
    set = { mb, memory -> mb.copy(memory = memory) })

val memorySize: Lens<Memory, GB> = Lens(
    get = { memory -> memory.size },
    set = { memory, size -> memory.copy(size = size) })

fun main() {
    val laptopX8 = Laptop(500.0, MotherBoard("X", Memory(8)))

    // 기존 값에서 새 랩탑을 만들려면 몇 가지 중첩된 복사 메소드와 참조를 사용해야 함
    val laptopX16 = laptopX8.copy(
        price = 780.0,
        motherBoard = laptopX8.motherBoard.copy(
            memory = laptopX8.motherBoard.memory.copy(
                size = laptopX8.motherBoard.memory.size * 2
            )
        )
    )

    println("laptopX16 = $laptopX16")

//    val laptopMemorySize: Lens<Laptop, GB> = laptopMotherBoard compose motherBoardMemory compose memorySize
//
//    val laptopX16_2 = laptopMemorySize.modify(laptopPrice.set(laptopX8, 780.0)) { size -> size * 2 }
//
//    println("laptopX16 = $laptopX16_2")

    val laptopMemorySize: Lens<Laptop, GB> = laptopMotherBoard compose motherBoardMemory compose memorySize

    val laptopX16_2 = laptopMemorySize.modify(laptopPrice.set(laptopX8, 780.0)) { size -> size * 2 }

    println("laptopX16 = $laptopX16_2")
}