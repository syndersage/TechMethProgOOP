import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Riddle;
import tmp.oop.Wisdom;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Aphorism, Proverb, Riddle ... tests")
public class WisdomDeriveTest {

    Wisdom w1;
    String input;

    @BeforeEach
    void w1Init() {
        w1 = null;
    }

    @Test
    void validRiddle() {
        input = "answer\ntext\n5";
        w1 = new Riddle();
        w1.in(new Scanner(input));
        assertTrue(w1.valid());
    }

    @Test
    void invalidRateRiddle() {
        input = "answer\ntext\n15";
        w1 = new Riddle();
        w1.in(new Scanner(input));
        assertFalse(w1.valid());
    }

    @Test
    void invalidEmptyAnswerRiddle() {
        input = " \ntext\n15";
        w1 = new Riddle();
        w1.in(new Scanner(input));
        assertFalse(w1.valid());
    }

    @Test
    void invalidEmptyTextRiddle() {
        input = "answer\n\n5";
        w1 = new Riddle();
        w1.in(new Scanner(input));
        assertFalse(w1.valid());
    }
}
