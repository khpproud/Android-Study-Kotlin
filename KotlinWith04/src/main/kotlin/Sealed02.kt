package four

// 봉인 클래스는 기본적으로 추상(abstract) 이며, open, final은 지정할 수 없음
// 인스턴스가 하나만 존재해야 하는 경우 하위클래스 대신 객체를 사용(상속 계층 보호)
sealed class Employee()

class Programmer: Employee()
class Manager: Employee()
class CEO: Employee()