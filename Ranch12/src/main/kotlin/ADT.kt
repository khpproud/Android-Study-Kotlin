/**
 * 대수적 데이터 타입(ADT) : 지정된 타입과 연관될 수 있는 서브 타입들의 폐집합(Closed Set)을 나타냄
 */

/**
 * enum 클래스의 각 항목은 이 클래스의 서브타입이다
 */
enum class StudentStatus {
    NOT_ENROLLED,
    ACTIVE,
    GRADUATED;
    var courseId: String? = null        // 이 속성은 ACTIVE 에만 사용됨
}

// ADT 타입의 장점 - 모든 타입을 처리했는지 컴파일러가 검사할 수 있음
fun studentMessage(status: StudentStatus3): String {
    return when (status) {
        is StudentStatus3.NotEnrolled -> "과정에 등록하세요"
        is StudentStatus3.Active -> "${status.courseId} 과정에 등록되었습니다"
        is StudentStatus3.Graduated -> "졸업을 축하합니다"
    }
}

class Student(var status: StudentStatus)

/**
 * sealed 클래스는 자신의 서브클래스 종류를 제한하기 위해 사용
 * enum 클래스의 각 항목은 하나의 인스턴스만 생성되지만
 * sealed 클래스에 속하는 서브클래스드들은 일반 클래스이므로 인스턴스 개수에 제한이 없다
 */
// 1.sealed 클래스의 모든 서브 클래스들을 독립적인 클래스로 정의
sealed class StudentStatus2
object NotEnrolled: StudentStatus2()
class Active(val courseId: String): StudentStatus2()
object Graduated: StudentStatus2()

// 2. sealed 클래스의 모든 서브클래스드르을 sealed 클래스 내부에 중첩된 클래스로 정의
sealed class StudentStatus3 {
    object NotEnrolled: StudentStatus3()
    class Active(val courseId: String): StudentStatus3()
    object Graduated: StudentStatus3()
}

fun main() {
    val student = Student(StudentStatus.NOT_ENROLLED)

    val active = Active("Kotlin100")

    val active2 = StudentStatus3.Active("Kotlin010")
    println(studentMessage(active2))
}