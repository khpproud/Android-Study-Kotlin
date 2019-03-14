package eighteen_two_five

import JavaAnnotaion
import JavaAnnotation2
import JavaAnnotation3

annotation class KotlinAnnotation(val no: Int, val name: String)

@KotlinAnnotation(10, "Park")
//@JavaAnnotation(10, "Park")       // 에러 자바 어노테이션을 이용할 때는 함수로 선언되어 있으므로 함수명을 명시해야 함
@JavaAnnotaion(intValue = 10, stringValue = "Park")
class Test{ }

// 자바 어노테이션의 함수명이 value이면 이름을 명시하지 않아도 됨
@JavaAnnotation2(10, strValue = "Kim")
class Test2{ }

// 배열의 함수가 선언된 자바 어노테이션 이용
@JavaAnnotation3(10, 20, 30, strValue = ["Kim", "Lee", "Shin"])
class Test3{ }
