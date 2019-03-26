package four

// Provider 제네릭 클래스
abstract class Provider<T> {
    // 인스턴스를 생성하는 추상 함수
    abstract fun creator(): T
    // 생성된 인스턴스를 저장하는 필드
    private var instance: T? = null
    // 인스턴스의 대체 구현을 제공하며 테스트에 사용되는 필드
    var override: T? = null

    // override 인스턴스가 설정된 경우 이를 반환하고, 인스턴스가 생성된 경우 이를 반환하거나 생성 메서드를 이용해 인스턴스를 생성하고 필드 채움
    fun get(): T = override ?: instance ?: creator().also {
        instance = it
    }
}

// 기본 정적 생성자를 이용한 인터페이스 정의
interface MarvelRepository {
    fun getAllCharacters(searchQuery: String?): String

    companion object: Provider<String>() {
        override fun creator(): String {
            return "Blah!!!"
        }
    }
}

fun main() {
    MarvelRepository.get()
    MarvelRepository.override = object : MarvelRepository {
        override fun getAllCharacters(searchQuery: String?): String {
            return "All characters!!!"
        }

    }.toString()
}