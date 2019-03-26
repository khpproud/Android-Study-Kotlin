// 동기화 사용
public class Singleton02 {
    private static Singleton02 instance = null;
    private Singleton02() {
    }

    private synchronized static void createInstance() {
        if (instance == null)
            instance = new Singleton02();
    }

    public static Singleton02 getInstance() {
        if (instance == null)
            createInstance();
        return instance;
    }
}
