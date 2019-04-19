package chap05

// sealed 를 사용한 클래스 계층 구조 제한
sealed class PrintOperation

// sealed 클래스는 서브 클래스를 가질 수 있지만 반드시 같은 파일 내에 정의되어야 함
class ShowNormalPrint(val message: String): PrintOperation()
object ShowErrorPrint: PrintOperation()

fun doPrintOperation(printOperation: PrintOperation) {
    // default로 else를 사용하지 않아도 됨
    when (printOperation) {
        is ShowNormalPrint -> println("state ok : ${printOperation.message}")
        ShowErrorPrint -> error("state error")
    }
}

fun main() {
    doPrintOperation(ShowNormalPrint("success"))
}