import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Client;
import tmp.oop.SingleLinkedContainer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SingleLinkedContainer tests")
public class ContainerTest {

    SingleLinkedContainer slc = new SingleLinkedContainer();

    StringWriter output = new StringWriter();

    String input;

    @BeforeEach
    public void initEach() {
        output = new StringWriter();
        slc.clear();
    }

    @Test
    void emptyListTest() {
        assertEquals(0, slc.getSize());
    }

    @Test
    void emptyInputTest() throws IOException {
        String str = "";
        slc.in(new Scanner(str));
        assertEquals(0, slc.getSize());
    }

    @Test
    void addElementTest() throws IOException {
        input = "1\nauthor\ntext\n5";
        slc.in(new Scanner(input));
        assertEquals(1, slc.getSize());
    }

    @Test
    void clearListTest() throws IOException {
        input = "1\nauthor\ntext\n5";
        slc.in(new Scanner(input));
        slc.clear();
        assertEquals(0, slc.getSize());
    }

    @Test
    void clearList2Test() throws IOException {
        input = "1\nauthor\ntext\n5\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(input));
        slc.clear();
        assertEquals(0, slc.getSize());
    }

    @Test
    void clearListFieldsTest() throws IOException {
        input = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(input));
        slc.clear();
        assertNull(slc.getHead());
    }

    @Test
    void clearListFields2Test() throws IOException {
        input = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(input));
        slc.clear();
        assertNull(slc.getTail());
    }

    @Test
    void addTwoElementsTest() throws IOException {
        input = "1\nauthor\ntext\n5\n2\ncountry\ntext\n10";
        slc.in(new Scanner(input));
        assertEquals(2, slc.getSize());
    }

    @Test
    void addThreeElementsTest() throws IOException {
        input = "1\nauthor\ntext\n5\n2\ncountry\ntext2\n10\n1\nauthor2\ntext3\n1";
        slc.in(new Scanner(input));
        assertEquals(3, slc.getSize());
    }

    @Test
    void emptyLinesTest() throws IOException {
        input = "\n\n\n1\nauthor\ntext\n5\n2\ncountry\ntext\n10";
        slc.in(new Scanner(input));
        assertEquals(2, slc.getSize());
    }

    @Test
    void spacesInWisdomTypeLineTest() throws IOException {
        input = "  \t1\r\nauthor\ntext\n5\n2\ncountry\ntext\n10";
        Client.verbose = true;
        slc.in(new Scanner(input));
        assertEquals(2, slc.getSize());
    }

    @Test
    void spacesInWisdomTypeLine2Test() throws IOException {
        input = " \r 1               \nauthor\ntext\n5\n2\ncountry\ntext\n10";
        slc.in(new Scanner(input));
        assertEquals(2, slc.getSize());
    }

    @Test
    void incorrectWisdomTypeTest() throws IOException {
        input = "\n\n\n4\nauthor\ntext\n5\n2\ncountry\ntext\n10";
        slc.in(new Scanner(input));
        assertEquals(1, slc.getSize());
    }

    @Test
    void emptyLineBetweenTypeAndTextTest() throws IOException {
        input = "1\n\ntext\n5\n2\ncountry\ntext\n10";
        slc.in(new Scanner(input));
        assertEquals(1, slc.getSize());
    }

    @Test
    void oneEmptyTextTest() throws IOException {
        input = "1\nauthor\n\n5";
        slc.in(new Scanner(input));
        assertEquals(0, slc.getSize());
    }

    @Test
    void twoEmptyTextAndRateTest() throws IOException {
        input = "1\nauthor\n\n\n3\nanswer\ntext\n8"; //символ "2" будет являться текстом для первой мудрости
        slc.in(new Scanner(input));
        assertEquals(1, slc.getSize());
    }

    @Test
    void nonExistingFileTest() {
        Path file = Path.of("ghdfjghjkdfgujdf");
        NoSuchFileException exception = assertThrows(NoSuchFileException.class, () -> slc.in(new Scanner(file)));
        assertEquals("ghdfjghjkdfgujdf", exception.getMessage());
    }

    @Test
    void existingFileTest() {
        Path file = Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ContainerTestData\\in");
        assertDoesNotThrow(() -> slc.in(new Scanner(file)));
    }

    @Test
    void addToListFromFileTest() throws IOException {
        Path file = Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ContainerTestData\\in");
        Files.writeString(file, "1\nauthor\ntext\n5\n2\ncountry\ntext\n7");
        slc.in(new Scanner(file));
        assertEquals(2, slc.getSize());
    }

    @Test
    void stringInputStringOutputTest() throws IOException {
        input = "1\nauthor\ntext\n2";
        slc.in(new Scanner(input));
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), "1: Aphorism: text. By: author. Rating score: 2\r\n");
    }

    @Test
    void sortOneNodeTest() throws IOException {
        input = "1\nauthor\ntext\n2";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), "1: Aphorism: text. By: author. Rating score: 2\r\n");
    }

    @Test
    void sortAlreadyCorrectOrderOfTwoNodesTest() throws IOException {
        input = "1\nauthor\ntext\n2\n2\ncountry\ntext!2\n8";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Aphorism: text. By: author. Rating score: 2\r
                2: Proverb: text!2. From: country. Rating score: 8\r
                """);
    }

    @Test
    void sortTwoSameCompareValueNodesTest() throws IOException {
        input = "1\nauthor\ntext\n2\n2\ncountry\ntext2\n8";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Aphorism: text. By: author. Rating score: 2\r
                2: Proverb: text2. From: country. Rating score: 8\r
                """);
    }

    @Test
    void sortIncorrectOrderOfTwoNodesTest() throws IOException {
        input = "1\nauthor\ntext\n2\n2\ncountry\ntext2\n8";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Aphorism: text. By: author. Rating score: 2\r
                2: Proverb: text2. From: country. Rating score: 8\r
                """);
    }

    @Test
    void sortFiveElementsTest() throws IOException {
        input = "1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Proverb: text2. From: country. Rating score: 8\r
                2: Proverb: te!xt2. From: country. Rating score: 8\r
                3: Aphorism: te!!xt. By: author. Rating score: 2\r
                4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
                5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
                """);
    }

    @Test
    void emptyLinesAtBeginningTest() throws IOException {
        input = "\n\n\n\n\n\n\n\n1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Proverb: text2. From: country. Rating score: 8\r
                2: Proverb: te!xt2. From: country. Rating score: 8\r
                3: Aphorism: te!!xt. By: author. Rating score: 2\r
                4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
                5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
                """);
    }

    @Test
    void emptyLinesAtStartAndBetweenWisdomElementsTest() throws IOException {
        input = "\n\n\n\n\n\n\n\n1\nauthor\nte!!xt\n2\n\n\n\n\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n\n\n\n\n2\ncountry\nte!xt2\n8\n\n\n1\nauthor\nte!!!xt2\n9";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Proverb: text2. From: country. Rating score: 8\r
                2: Proverb: te!xt2. From: country. Rating score: 8\r
                3: Aphorism: te!!xt. By: author. Rating score: 2\r
                4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
                5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
                """);
    }

    @Test
    void emptyLinesAtTheEndTest() throws IOException {
        input = "1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9\n\n\n\n\n    ";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Proverb: text2. From: country. Rating score: 8\r
                2: Proverb: te!xt2. From: country. Rating score: 8\r
                3: Aphorism: te!!xt. By: author. Rating score: 2\r
                4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
                5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
                """);
    }

    @Test
    void blankLinesBeforeAfterBetweenWisdomElementsTest() throws IOException {
        input = "    \n     1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n\t\r\t\r3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n  \t \n \r    1\nauthor\nte!!!xt2\n9\n   \r\r\r   \r\n\n\t\n\n    ";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), """
                1: Proverb: text2. From: country. Rating score: 8\r
                2: Proverb: te!xt2. From: country. Rating score: 8\r
                3: Aphorism: te!!xt. By: author. Rating score: 2\r
                4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
                5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
                """);
    }

    @Test
    void sortEmptyTest() throws IOException {
        input = "";
        slc.in(new Scanner(input));
        slc.sort();
        slc.out(new PrintWriter(output));
        assertEquals(output.toString(), "");
    }
}
