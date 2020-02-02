package chap10.dsl

import chap10.dsl.Employee.Companion.employee

data class Employee(
    val name: String,
    val department: String,
    val salary: Double,
    val enrolledYear: Int
) {
    private constructor(builder: Builder)
            : this(builder.name, builder.department, builder.salary, builder.enrolledYear)

    private constructor(dsl: DSL) : this(dsl.name, dsl.department, dsl.salary, dsl.enrolledYear)

    class Builder {
        var name: String = ""
            private set
        var department: String = ""
            private set
        var salary: Double = 0.0
            private set
        var enrolledYear: Int = 0
            private set

        fun name(name: String) = apply { this.name = name }

        fun department(department: String) = apply { this.department = department }

        fun salary(salary: Double) = apply { this.salary = salary }

        fun enrolledYear(enrolledYear: Int) = apply { this.enrolledYear = enrolledYear }

        fun build() = Employee(this)
    }

    companion object {
        inline fun employee(block: DSL.() -> Unit) = DSL().apply(block).build()
    }

    class DSL {
        var name: String = ""
        var department: String = ""
        var salary: Double = 0.0
        var enrolledYear: Int = 0

        fun build() = Employee(this)
    }
}

fun main() {
    // Builder pattern usage
    val employee = Employee.Builder()
        .name("Kim")
        .department("HR")
        .salary(15000.0)
        .enrolledYear(10)
        .build()

    // DSL usage
    val employee2 = employee {
        name = "Kim"
        department = "HR"
        salary = 50000.0
        enrolledYear = 3
    }

    println(employee)
    println(employee2)
}