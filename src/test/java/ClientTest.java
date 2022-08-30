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
         Client.main(new String[]{"input", "output", "-v"});
         assertTrue(Client.verbose);
    }

    @Test
    void argument2Test() {
         Client.main(new String[]{"input", "output", "йцу"});
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
                 Container contains 2 elements.\r
                 1: Aphorism: text. By: author. Rating score: 5\r
                 2: Proverb: text3. From: country. Rating score: 7\r
                 Empty container.\r
                 Container contains 0 elements.\r
                 """;
         assertEquals(str, Files.readString(Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out")));
    }
}
