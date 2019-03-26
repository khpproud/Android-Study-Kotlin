package four

// 봉인 클래스 사용 예
sealed class Vehicle()
class Truck: Vehicle()
class Bus: Vehicle()

fun whenTest(vehicle: Vehicle) {
    when (vehicle) {
        is Bus -> println("Can transport 50 peoples")
        is Truck -> println("Can transport furniture")
    }
}