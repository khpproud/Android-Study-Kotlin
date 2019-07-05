package ch05_class

// 연관 관계 나타내기
class Patient(val name: String) {
    fun doctorList(d: Doctor) {             // Doctor 를 인자로 참조
        println("Patient : $name, Doctor : ${d.name}")
    }
}
class Doctor(val name: String) {
    fun patientList(p: Patient) {           // Patient 를 인자로 참조
        println("Doctor : $name, Patient : ${p.name}")
    }
}

fun main() {
    val doc1 = Doctor("Park")
    val patient1 = Patient("Koo")
    doc1.patientList(patient1)
    patient1.doctorList(doc1)
}