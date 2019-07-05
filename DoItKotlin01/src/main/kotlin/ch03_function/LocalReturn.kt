package ch03_function

// return 으로 람다식 빠져나오기
fun main() {
    shortFunc(3) {
        println("First call : $it")
        return                          // out()이 인라인되어 대체되기 때문에 return 문도 포함되게 되는데 이를 비지역 반환이라 함
    }
}