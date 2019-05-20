/**
 * 자바 8은 객체지향 프로그래밍과 람다 표현식을 지원 하지만 함수의 매개변수나 변수에 함수를 정의할 수 있는 기능은 없다
 * 대신에 익명 내부 클래스(Anonymous Inner Class)를 제공
 */
public class AnonymousInnerClassExample {
    public static void main(String[] args) {
        Greeting greeting = (playerName, numBuildings) -> {
            int currentYears = 2019;
            System.out.println(numBuildings + " 채의 건물이 추가됨");
            return "SimVillage 방문을 환영, " + playerName + "! (copyright " + currentYears + ")";
        };

        System.out.println(greeting.greet("Lee", 6));
    }

    /**
     * 자바에서는 람다를 정의하는 함수를 나타내기 위해 이름이 있는 타입(인터페이스 or 클래스)이 필요
     */
    @FunctionalInterface
    public interface Greeting {
        String greet(String playerName, int numBuildings);
    }
}
