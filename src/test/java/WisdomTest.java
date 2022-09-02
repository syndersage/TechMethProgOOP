import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Aphorism;
import tmp.oop.Proverb;
import tmp.oop.Riddle;
import tmp.oop.Wisdom;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Wisdom tests")
public class WisdomTest {

    String input;
    Wisdom w1;

    StringWriter str;

    @BeforeEach
    void w1Init() {
        w1 = null;
        str = new StringWriter();
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
    void setCorrectRateTest() {
        input = "5";
        w1 = new Aphorism();
        w1.inRate(new Scanner(input));
        assertEquals(5, w1.getRate());
    }

    @Test
    void setCorrectTextTest() {
        input = "h e l l o";
        w1 = new Aphorism();
        w1.inText(new Scanner(input));
        assertEquals(input, w1.getText());
    }

    @Test
    void setIncorrectBlankTextTest() {
        input = "        ";
        w1 = new Aphorism();
        assertThrows(NoSuchElementException.class, () -> w1.inText(new Scanner(input)));
    }


    @Test
    void setCorrectDataParamsTest() {
        input = "author";
        w1 = new Aphorism();
        assertDoesNotThrow(() -> w1.inData(new Scanner(input)));
        w1.out(new PrintWriter(str));
        assertEquals("Aphorism: null. By: " + input + ". Rating score: 0\r\n", str.toString());
    }

    @Test
    void setIncorrectEmptyDataParamsTest() {
        input = "";
        w1 = new Aphorism();
        assertThrows(NoSuchElementException.class, () -> w1.inData(new Scanner(input)));
        w1.out(new PrintWriter(str));
        assertEquals("Aphorism: null. By: null. Rating score: 0\r\n", str.toString());
    }

    @Test
    void setIncorrectBlankDataParamsTest() {
        input = " \t \r  ";
        w1 = new Aphorism();
        assertThrows(NoSuchElementException.class, () -> w1.inData(new Scanner(input)));
        w1.out(new PrintWriter(str));
        assertEquals("Aphorism: null. By: null. Rating score: 0\r\n", str.toString());
    }

    @Test
    void compareToMethodTest() {
        Wisdom riddle = new Riddle();
        Wisdom aphorism = new Aphorism();
        Wisdom proverb = new Proverb();
        riddle.inText(new Scanner("one ! punctuation mark"));
        aphorism.inText(new Scanner("two :: punctuation marks"));
        proverb.inText(new Scanner("zero punctuation marks"));
        assertTrue(riddle.compareTo(aphorism) < 0);
        assertTrue(riddle.compareTo(proverb) > 0);
        proverb.inText(new Scanner("one ! punctuation marks"));
        assertEquals(0, riddle.compareTo(proverb));
    }
}
