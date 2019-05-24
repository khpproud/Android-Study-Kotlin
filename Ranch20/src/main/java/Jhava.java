import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Java와 Kotlin 코드의 상호 운용 예
 */
public class Jhava {
    // 타입 매핑
    public int hitPoints = 52489112;
    private int hitPoints2 = hitPoints;
    private String greeting = "BLARGH";

    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    // 상호 운용과 null 처리
    @Nullable
    public String determineFirendshipLevel() {
        return null;
    }

    public int getHitPoints2() {
        return hitPoints2;
    }

    public void setHitPoints2(int hitPoints2) {
        this.hitPoints2 = hitPoints2;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void offerFood() {
        Hero.handOverFood("피자");
    }

    // 예외와 상호 운용
    public void extendHandInFriendship() throws Exception {
        throw new Exception();
    }

    public void apologize() {
        try {
            Hero.acceptApology();
        } catch (IOException e) {
            System.err.println("Caught!");
        }
    }

    public static void main(String[] args) {
        System.out.println(Hero.makeProclamation());

        System.out.println("Spells:");
        Spellbook spellbook = new Spellbook();
        for (String spell : spellbook.spells)
            System.out.println(spell);

//        System.out.println("Max spell count : " + Spellbook.Companion.getMAX_SPELL_COUNT());
        System.out.println("Max spell count : " + Spellbook.MAX_SPELL_COUNT);

        Spellbook.getSpellbookGreeting();

        Function1<String, Unit> translatorJ = Hero.getTranslator();
        translatorJ.invoke("TRUCE");
    }
}
