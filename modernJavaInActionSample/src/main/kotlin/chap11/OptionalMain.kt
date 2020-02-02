package chap11

import java.util.*
import java.util.stream.Collectors.toSet

private fun getCarInsuranceNameNullSafe(person: Person?): String {
    if (person != null) {
        val car = person.car
        if (car != null) {
            val insurance = car.insurance
            if (insurance != null) {
                return insurance.name
            }
        }
    }
    return "Unknown"
}

private fun getCarInsuranceNameNullSafe2(person: Person?): String {
    person ?: return "Unknown"
    val car = person.car
    car ?: return "Unknown"
    val insurance = car.insurance
    insurance ?: return "Unknown"
    return insurance.name
}

//private fun getCarInsuranceNameNotCompiled(person: OptionalPerson): String {
//    val optPerson = Optional.of(person)
//    val name = optPerson.map(OptionalPerson::optCar)
//        .map(OptionalCar::optInsurance)
//        .map(Insurance::name)
//    return name.orElse("Unknown")
//}

private fun getCarInsuranceName(person: Optional<OptionalPerson>): String {
    return person.flatMap(OptionalPerson::optCar)
        .flatMap(OptionalCar::optInsurance)
        .map(Insurance::name)
        .orElse("Unknown")
}

private fun getCarInsuranceNames(persons: List<OptionalPerson>): Set<String> {
    return persons.stream()
        .map(OptionalPerson::optCar)
        .map { optCar -> optCar.flatMap(OptionalCar::optInsurance) }
        .map { optInsurance -> optInsurance.map(Insurance::name) }
        .flatMap(Optional<String>::stream)
        .collect(toSet())
}

private fun findCheapestInsurance(person: OptionalPerson, car: OptionalCar): Insurance {
    // Fancy computed searching service
    // ...
    return Insurance("Seoul")
}

private fun nullSafeFindCheapestInsurance(person: Optional<OptionalPerson>, car: Optional<OptionalCar>):
        Optional<Insurance> {
    return person.flatMap { p: OptionalPerson -> car.map { c: OptionalCar -> findCheapestInsurance(p, c) } }
}

private fun getCarInsuranceNameOverMinAge(person: Optional<OptionalPerson>, minAge: Int): String {
    return person.filter { p -> p.age >= minAge }
        .flatMap(OptionalPerson::optCar)
        .flatMap(OptionalCar::optInsurance)
        .map(Insurance::name)
        .orElse("Unknown")
}