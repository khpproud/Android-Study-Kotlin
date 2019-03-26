// 싱클톤 기본 정의(스레드로 부터 안전하지 않음)
public class Singleton01 {
    private Singleton01() {
    }

    private static Singleton01 instance;

    public static Singleton01 getInstance() {
        if (instance == null)
            instance = new Singleton01();
        return instance;
    }
}
