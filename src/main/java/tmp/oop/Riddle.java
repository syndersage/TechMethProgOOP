package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс является описанием одного из типов мудрости - загадки.
 * В классе находятся только поля, относящиеся к загадке, все общие поля располагаются в Wisdom.
 * Описываются методы, которые работают исключительно с собственными полями.
 */
public class Riddle extends Wisdom {

  //Ответ на загадку
  private String answer;

  /**
   * В экземпляр построчно передается иноформация из переданного источника о загадке и ответе на
   * неё
   *
   * @param scan источник иноформации
   */
  @Override
  public void inData(Scanner scan) throws NoSuchElementException {
    String line;
    line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Answer must be at least one symbol length.");
    }
    this.answer = line;
  }

  @Override
  public void inPairWith(Wisdom secondWisdom, PrintWriter pw) {
    secondWisdom.inPairWithRiddle(pw);
  }

  @Override
  public void inPairWithAphorism(PrintWriter pw) {
    pw.println("Aphorism and Riddle");
  }

  @Override
  public void inPairWithProverb(PrintWriter pw) {
    pw.println("Proverb and Riddle");
  }

  @Override
  public void inPairWithRiddle(PrintWriter pw) {
    pw.println("Riddle and Riddle");
  }

  /**
   * Выводит информацию о загадке и ответе на неё
   *
   * @param pw ресурс для вывода информации
   */
  @Override
  public void out(PrintWriter pw) {
    pw.println("Riddle: " + getText() + ". Answer: " + answer + ". Rating score: " + getRate());
  }

  /**
   * Экземпляр загадки проверяется на то, что текст или ответ не null и не пустые
   *
   * @return boolean - можно или нет считать экземпляр валидным
   */
  @Override
  public boolean valid() {
    return super.valid() & answer != null && !answer.isBlank();
  }
}
