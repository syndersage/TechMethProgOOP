package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс является описанием одного из типов мудрости - афоризма.
 * В классе находятся только поля, относящиеся к афоризму, все общие поля располагаются в Wisdom.
 * Описываются методы, которые работают исключительно с собственными полями.
 */
public class Aphorism extends Wisdom {

  //Автор афоризмы
  private String author;

  /**
   * В экземпляр построчно передается иноформация из переданного источника об авторе и афоризме
   *
   * @param scan источник иноформации
   * @throws NoSuchElementException если не удалось прочитать строку либо она пустая
   */
  @Override
  public void inData(Scanner scan) throws NoSuchElementException {
    String line;
    line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Author must be at least one symbol length.");
    }
    this.author = line;
  }

  @Override
  public void inPairWith(Wisdom secondWisdom, PrintWriter pw) {
    secondWisdom.inPairWithAphorism(pw);
  }

  @Override
  public void inPairWithAphorism(PrintWriter pw) {
    pw.println("Aphorism and Aphorism");
  }

  @Override
  public void inPairWithProverb(PrintWriter pw) {
    pw.println("Proverb and Aphorism");
  }

  @Override
  public void inPairWithRiddle(PrintWriter pw) {
    pw.println("Riddle and Aphorism");
  }

  /**
   * Выводит информацию об афоризме и авторе
   *
   * @param pw ресурс для вывода информации
   */
  @Override
  public void out(PrintWriter pw) {
    pw.println("Aphorism: " + getText() + ". By: " + author + ". Rating score: " + getRate());
  }

  /**
   * Экземпляр афоризмы проверяется на валидность родительского метода и то что поле автор не null и
   * не пустое Экземпляр афоризмы проверяется на то, что текст или автор не null и не пустые
   *
   * @return boolean - можно или нет считать экземпляр валидным
   */
  @Override
  public boolean valid() {
    return super.valid() & author != null && !author.isBlank();
  }
}
