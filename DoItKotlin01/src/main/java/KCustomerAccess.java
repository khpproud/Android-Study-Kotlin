import ch06.property.KCustomer;
import ch06.property.KJob;

// KJob에 대한 접근 방법
public class KCustomerAccess {
    public static void main(String[] args) {
        // 코틀린 클래스의 컴패니언 객체에 대한 접근
        System.out.println("KCustomer.LEVEL = " + KCustomer.LEVEL);
        KCustomer.login();                          // 어노테이션을 사용할 때 접근법
        KCustomer.Companion.login();                // 어노테이션을 사용하지 않았을 때 접근법

        // KJob에 대한 객체 생성 후 접근
        KJob kjob = KCustomer.JOB;
        System.out.println(kjob.getTitle());

        // KCustomer 를 통한 접근
        KCustomer.JOB.setTitle("Accountant");
        System.out.println(KCustomer.JOB.getTitle());
    }
}
