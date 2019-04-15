package chap05

// DSL 생성의 예(XML Ver)
/**
 * <bicycle description = "Fast carbon commuter">
 *     <bar material="ALUMINIUM" type="FLAT">
 *     </bar>
 *     <frame material="CARBON">
 *         <wheel brake="DISK" material="ALUMINIUM">
 *         </wheel>
 *     </frame>
 *     <fork material="CARBON">
 *         <wheel brake="DISK" material="ALUMINIUM">
 *         </wheel>
 *     </fork>
 * </bicycle>
 */

// DSL 에서 자전거의 모든 부분이 Element 인터페이스를 확장/구현 할 것임
interface Element {
    fun render(builder: StringBuilder, indent: String)
}

@DslMarker
annotation class ElementMarker

// 내부 요소가 외부 요소에 도달되는것을 방지
// 자전거 각 부분을 나타낼 Part 추상클래스
@ElementMarker
abstract class Part(private val name: String): Element {
    private val children = arrayListOf<Element>()
    protected val attributes = hashMapOf<String, String>()
    protected fun <T: Element> initElement(element: T, init: T.() -> Unit): T {
        element.init()
        children.add(element)
        return element
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        children.forEach {
            c -> c.render(builder, indent + "\t")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String = buildString {
        attributes.forEach { (attr, value) -> append(" $attr=\"$value\"") }
    }

    override fun toString(): String = buildString {
        render(this, "")
    }
}


// 자전거 각 부분의 재료를 나타내는 추상 클래스
abstract class PartWithMaterial(name: String): Part(name) {
    var material: Material
        get() = Material.valueOf(attributes["material"]!!)
        set(value) {
            attributes["material"] = value.name
        }
}

// 자전거 구성 재료의 명세
enum class Material {
    CARBON, STEEL, TITANIUM, ALUMINIUM
}

enum class BarType {
    DROP, FLAT, TT, BULLHORN
}

enum class Brake {
    RIM, DISK
}

// 자전거 객체 정의
class Bicycle: Part("bicycle") {
    fun description(description: String) {
        attributes["description"] = description
    }

    fun frame(init: Frame.() -> Unit) = initElement(Frame(), init)

    fun fork(init: Fork.() -> Unit) = initElement(Fork(), init)

    fun bar(init: Bar.() -> Unit) = initElement(Bar(), init)
}

// Frame 정의
class Frame: PartWithMaterial("frame") {
    fun backWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}

// Wheel 정의
class Wheel: PartWithMaterial("Wheel") {
    var brake: Brake
        get() = Brake.valueOf(attributes["brake"]!!)
        set(value) {
            attributes["brake"] = value.name
        }
}

// Bar 정의
class Bar: PartWithMaterial("bar") {
    var barType: BarType
        get() = BarType.valueOf(attributes["type"]!!)
        set(value) {
            attributes["type"] = value.name
        }
}

// Fork 앞바퀴에 대한 함수 정의
class Fork: PartWithMaterial("fork") {
    fun frontWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}

// DSL용 입력 함수
fun bicycle(init: Bicycle.() -> Unit): Bicycle {
    val cycle = Bicycle()
    cycle.init()
    return cycle
}

fun main() {
    val commuter = bicycle {
        description("Fast carbon commuter")
        bar {
            barType = BarType.FLAT
            material = Material.ALUMINIUM
        }
        frame {
            material = Material.CARBON
            backWheel {
                material = Material.ALUMINIUM
                brake = Brake.DISK
            }
        }
        fork {
            material = Material.CARBON
            frontWheel {
                material = Material.ALUMINIUM
                brake = Brake.DISK
            }
        }
    }

    println(commuter)
}
