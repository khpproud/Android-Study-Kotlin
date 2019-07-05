package ch05_class

// 의존 관계 나타내기
class Patient2(val name: String, var id: Int) {
    fun doctorList(d: Doctor2) {
        println("Patient : $name, Doctor : ${d.name}")
    }
}

class Doctor2(val name: String, val p: Patient2) {
    val customerId: Int = p.id

    fun patientList() {
        println("Doctor : $name, Patient : ${p.name}")
        println("Patient Id : $customerId")
    }
}

fun main() {
    val patient1 = Patient2("Koo", 1234)
    val doc1 = Doctor2("Park", patient1)
    doc1.patientList()
}