import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.oop.Client;

@DisplayName("Client tests")
public class ClientTest {

  @BeforeEach
  void clearOutputFile() throws IOException {
    Files.writeString(Path.of(
            "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out"),
        "");
    Files.writeString(Path.of(
            "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2"),
        "");
    Files.writeString(Path.of(
            "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out3.txt"),
        "");
    Files.writeString(Path.of(
            "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\emptyOut"),
        "");
    Client.arguments.help = false;
    Client.arguments.verbose = false;
    Client.arguments.pair = false;
    Client.arguments.sort = false;
  }

  @Test
  void argumentTest() {
    Client.main(new String[]{"-p", "-i",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\emptyIn",
        "-s", "-o",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\emptyOut",
        "-v"});
    assertTrue(Client.arguments.verbose);
    assertTrue(Client.arguments.pair);
    assertTrue(Client.arguments.sort);
  }

  @Test
  void argument2Test() {
    Client.main(new String[]{"input.txt", "output.txt", "йцу"});
    assertFalse(Client.arguments.verbose);
  }

  @Test
  void argument3Test() {
    Client.main(new String[]{
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out"});
    assertFalse(Client.arguments.verbose);
  }

  @Test
  void correctPrintWriteTest() throws IOException {
    Client.main(new String[]{"-i",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in",
        "-o",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out",
        "-v", "-s", "-p"});
    String str = """
        Filled container.\r
        Container contains 2 elements.\r
        1: Aphorism: text. By: author. Rating score: 5\r
        2: Proverb: text3. From: country. Rating score: 7\r
        \r
        Iterating every pair:\r
        Aphorism and Proverb\r
        \r
        Empty container.\r
        Container contains 0 elements.\r
        """;
    assertEquals(str, Files.readString(Path.of(
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out")));
  }

  @Test
  void correctPrintWrite2Test() throws IOException {
    Client.main(new String[]{"-i",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in2",
        "-o",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2",
        "-v", "-s"});
    String str = """
        Filled container.\r
        Container contains 3 elements.\r
        1: Proverb: Still waters run deep. From: England. Rating score: 5\r
        2: Proverb: Не плюй в колодец, пригодится воды напиться. From: Россия. Rating score: 4\r
        3: Aphorism: Не беспокойся о том, что тебя не знают. Беспокойся о том, достоин ли ты того, чтобы тебя знали.. By: Конфуций. Rating score: 10\r
        \r
        Empty container.\r
        Container contains 0 elements.\r
        """;
    assertEquals(str, Files.readString(Path.of(
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2")));
  }

  @Test
  void correctPrintWrite3Test() throws IOException {
    Client.main(new String[]{"-i",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\in2",
        "-o",
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2",
        "-p", "-v"});
    String str = """
        Filled container.\r
        Container contains 3 elements.\r
        1: Aphorism: Не беспокойся о том, что тебя не знают. Беспокойся о том, достоин ли ты того, чтобы тебя знали.. By: Конфуций. Rating score: 10\r
        2: Proverb: Не плюй в колодец, пригодится воды напиться. From: Россия. Rating score: 4\r
        3: Proverb: Still waters run deep. From: England. Rating score: 5\r
        \r
        Iterating every pair:\r
        Aphorism and Proverb\r
        Aphorism and Proverb\r
        Proverb and Proverb\r
        \r
        Empty container.\r
        Container contains 0 elements.\r
        """;
    assertEquals(str, Files.readString(Path.of(
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ClientTestData\\out2")));
  }
}
