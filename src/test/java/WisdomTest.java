import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Aphorism;
import tmp.oop.Wisdom;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Wisdom tests")
public class WisdomTest {

    String input;
    Wisdom w1;

    @BeforeEach
    void w1Init() {
        w1 = null;
    }

    @Test
    void setGetCorrectRateTest() {
        w1 = new Aphorism();
        byte[] expectedResults = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        byte[] actualResults = new byte[expectedResults.length];
        for (int i = 0; i < expectedResults.length; i++) {
            w1.inRate(new Scanner(String.valueOf(i + 1)));
            actualResults[i] = w1.getRate();
        }
        assertArrayEquals(expectedResults, actualResults);
    }

    @Test
    void setGetIncorrectRateLowerThanMinTest() {
        input = "-5";
        Wisdom w1 = new Aphorism();
        assertThrows(NumberFormatException.class, () -> w1.inRate(new Scanner(input)));
    }

    @Test
    void setGetIncorrectRateHigherThanMaxTest() {
        input = "15";
        w1 = new Aphorism();
        assertThrows(NumberFormatException.class, () -> w1.inRate(new Scanner(input)));
    }

    @Test
    void setGetIncorrectRateNotNumberTest() {
        input = "rate";
        w1 = new Aphorism();
        assertThrows(NumberFormatException.class, () -> w1.inRate(new Scanner(input)));
    }

    @Test
    void setGetTextTest() {
        input = "5";
        w1 = new Aphorism();
        w1.inRate(new Scanner(input));
        assertEquals(5, w1.getRate());
    }
}
