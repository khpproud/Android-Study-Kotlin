package chap11

import java.util.*

data class Insurance(val name: String)

data class Car(val insurance: Insurance?)

data class Person(val car: Car?)

data class OptionalCar(val optInsurance: Optional<Insurance>)

data class OptionalPerson(val optCar: Optional<OptionalCar>, val age: Int)