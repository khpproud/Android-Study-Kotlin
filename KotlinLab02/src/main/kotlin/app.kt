import org.apache.commons.lang3.StringUtils
import java.util.*
import java.sql.Date as SQLDate

var myBool = false
val myVal: String = "hello"
get() {
    if (myBool)
        return field
    else
        return field.toUpperCase()
}

fun main() {
    val data = "  Hello  World !! Park"
    println(StringUtils.deleteWhitespace(data))

    val date = Date()
    val date2 = SQLDate(System.currentTimeMillis())

    println(date)
    println(date2)

    println(myVal)
    myBool = true
    println(myVal)
}