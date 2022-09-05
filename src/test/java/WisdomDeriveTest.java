import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Riddle;
import tmp.oop.Wisdom;

@DisplayName("Aphorism, Proverb, Riddle, ... tests")
public class WisdomDeriveTest {

  Wisdom w1;
  String input;

  Scanner scan;

  @BeforeEach
  void w1Init() {
    w1 = null;
  }

  @Test
  void validRiddle() {
    input = "answer\ntext\n5";
    scan = new Scanner(input);
    w1 = new Riddle();
    assertDoesNotThrow(() -> w1.inData(scan));
    w1.inText(scan);
    w1.inRate(scan);
    assertTrue(w1.valid());
  }

  @Test
  void invalidRateRiddle() {
    input = "answer\ntext\n15";
    w1 = new Riddle();
    scan = new Scanner(input);
    assertDoesNotThrow(() -> w1.inData(scan));
    assertDoesNotThrow(() -> w1.inText(scan));
    assertThrows(NumberFormatException.class, () -> w1.inRate(scan));
  }

  @Test
  void invalidEmptyAnswerRiddle() {
    input = " \ntext\n15";
    w1 = new Riddle();
    scan = new Scanner(input);
    assertThrows(NoSuchElementException.class, () -> w1.inData(scan));
    w1.inText(scan);
    assertThrows(NumberFormatException.class, () -> w1.inRate(scan));
    assertFalse(w1.valid());
  }

  @Test
  void invalidEmptyTextRiddle() {
    input = "answer\n\n5";
    w1 = new Riddle();
    scan = new Scanner(input);
    assertDoesNotThrow(() -> w1.inData(scan));
    assertThrows(NoSuchElementException.class, () -> w1.inText(scan));
    w1.inRate(scan);
    assertFalse(w1.valid());
  }
}
