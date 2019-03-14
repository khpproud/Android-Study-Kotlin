// 인터페이스 등록 setter 함수 이용하는 자바 소스
public class SamTest {
    public static void main(String... args) {
        SamClass obj = new SamClass();
        obj.setInterface(new JavaInterface1() {
            @Override
            public void callback() {
                System.out.println("hello java");
            }
        });

        // 람다로 변경 : SAM 기법
        obj.setInterface(() -> System.out.println("hello java"));
        obj.callback.callback();
    }
}
