import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tmp.oop.SingleLinkedContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

@DisplayName("add and clear operations at SingleLinkedContainer")
public class ContainerTest {

    SingleLinkedContainer slc = new SingleLinkedContainer();

    @BeforeEach
    public void initEach(){
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
        String str = "1\nauthor\ntext";
        slc.in(new Scanner(str));
        assertEquals(1, slc.getSize());
    }

    @Test
    void clearListTest() throws IOException {
        String str = "1\nauthor\ntext";
        slc.in(new Scanner(str));
        slc.clear();
        assertEquals(0, slc.getSize());
    }

    @Test
    void clearList2Test() throws IOException {
        String str = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(str));
        slc.clear();
        assertEquals(0, slc.getSize());
    }

    @Test
    void clearListFieldsTest() throws IOException {
        String str = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(str));
        slc.clear();
        assertNull(slc.getHead());
    }

    @Test
    void clearListFields2Test() throws IOException {
        String str = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(str));
        slc.clear();
        assertNull(slc.getTail());
    }

    @Test
    void addTwoElementsTest() throws IOException {
        String str = "1\nauthor\ntext\n2\ncountry\ntext";
        slc.in(new Scanner(str));
        assertEquals(2, slc.getSize());
    }

    @Test
    void addThreeElementsTest() throws IOException {
        String str = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
        slc.in(new Scanner(str));
        assertEquals(3, slc.getSize());
    }

    @Test
    void emptyLinesTest() throws IOException {
        String str = "\n\n\n1\nauthor\ntext\n2\ncountry\ntext";
        slc.in(new Scanner(str));
        assertEquals(2, slc.getSize());
    }

    @Test
    void incorrectWisdomTypeTest() throws IOException {
        String str = "\n\n\n3\nauthor\ntext\n2\ncountry\ntext";
        slc.in(new Scanner(str));
        assertEquals(1, slc.getSize());
    }

    @Test
    void emptyAuthorOrCountryTest() throws IOException {
        String str = "1\n\ntext\n2\ncountry\ntext";
        slc.in(new Scanner(str));
        assertEquals(1, slc.getSize());
    }

    @Test
    void oneEmptyTextTest() throws IOException {
        String str = "1\nauthor";
        slc.in(new Scanner(str));
        assertEquals(0, slc.getSize());
    }

    @Test
    void twoEmptyTextsTest() throws IOException {
        String str = "1\nauthor\n2\ncountry"; //символ "2" будет являться текстом для первой мудрости
        slc.in(new Scanner(str));
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
        Path file = Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\in");
        assertDoesNotThrow(() -> slc.in(new Scanner(file)));
    }

    @Test
    void addToListFromFileTest() throws IOException {
        Path file = Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\in");
        Files.writeString(file, "1\nauthor\ntext\n2\ncountry\ntext");
        slc.in(new Scanner(file));
        assertEquals(2, slc.getSize());
    }
}
