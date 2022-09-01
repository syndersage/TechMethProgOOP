import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Client tests")
public class ClientTest {

    @BeforeEach
    void clearOutputFile() throws IOException {
        Files.writeString(Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out"), "");
    }

    @Test
    void argumentTest() {
        Client.main(new String[]{"input.txt", "output.txt", "-v"});
        assertTrue(Client.verbose);
    }

    @Test
    void argument2Test() {
        Client.main(new String[]{"input.txt", "output.txt", "йцу"});
        assertFalse(Client.verbose);
    }

    @Test
    void argument3Test() {
        Client.main(new String[]{"F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in", "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out"});
        assertFalse(Client.verbose);
    }

    @Test
    void correctPrintWriteTest() throws IOException {
        Client.main(new String[]{"F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in", "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out"});
        String str = """
                Filled container.\r
                \r
                Container contains 2 elements.\r
                1: Aphorism: text. By: author. Rating score: 5\r
                2: Proverb: text3. From: country. Rating score: 7\r
                \r
                Sorted container:\r
                1: Aphorism: text. By: author. Rating score: 5\r
                2: Proverb: text3. From: country. Rating score: 7\r
                \r
                Iterating every pair:\r
                Aphorism and Proverb\r
                \r
                Empty container.\r
                Container contains 0 elements.\r
                """;
        assertEquals(str, Files.readString(Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out")));
    }

    @Test
    void correctPrintWrite2Test() throws IOException {
        Client.main(new String[]{"F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in2", "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2"});
        String str = """
                Filled container.\r
                \r
                Container contains 3 elements.\r
                1: Aphorism: Не беспокойся о том, что тебя не знают. Беспокойся о том, достоин ли ты того, чтобы тебя знали.. By: Конфуций. Rating score: 10\r
                2: Proverb: Не плюй в колодец, пригодится воды напиться. From: Россия. Rating score: 4\r
                3: Proverb: Still waters run deep. From: England. Rating score: 5\r
                \r
                Sorted container:\r
                1: Proverb: Still waters run deep. From: England. Rating score: 5\r
                2: Proverb: Не плюй в колодец, пригодится воды напиться. From: Россия. Rating score: 4\r
                3: Aphorism: Не беспокойся о том, что тебя не знают. Беспокойся о том, достоин ли ты того, чтобы тебя знали.. By: Конфуций. Rating score: 10\r
                \r
                Iterating every pair:\r
                Proverb and Proverb\r
                Proverb and Aphorism\r
                Proverb and Aphorism\r
                \r
                Empty container.\r
                Container contains 0 elements.\r
                """;
        assertEquals(str, Files.readString(Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2")));
    }
}
