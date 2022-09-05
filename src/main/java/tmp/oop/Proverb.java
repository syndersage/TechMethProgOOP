package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Proverb extends Wisdom {

  //Страна
  private String country;

  /**
   * В экземпляр построчно заносится иноформация из переданного источника о стране и пословице
   *
   * @param scan источник иноформации
   */
  @Override
  public void inData(Scanner scan) throws NoSuchElementException {
    String line;
    line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Country must be at least one symbol length.");
    }
    this.country = line;
  }

  @Override
  public void inPairWith(Wisdom secondWisdom, PrintWriter pw) {
    secondWisdom.inPairWithProverb(pw);
  }

  @Override
  public void inPairWithAphorism(PrintWriter pw) {
    pw.println("Aphorism and Proverb");
  }

  @Override
  public void inPairWithProverb(PrintWriter pw) {
    pw.println("Proverb and Proverb");
  }

  @Override
  public void inPairWithRiddle(PrintWriter pw) {
    pw.println("Riddle and Proverb");
  }

  /**
   * Выводит информацию о пословице и сране происхождения
   *
   * @param pw ресурс для вывода информации
   */
  @Override
  public void out(PrintWriter pw) {
    pw.println("Proverb: " + getText() + ". From: " + country + ". Rating score: " + getRate());
  }

  /**
   * Экземпляр пословицы проверяется на валидность родительского метода и то что поле страна не null
   * и не пустое Экземпляр пословицы проверяется на то, что текст или страна не null и не пустые
   *
   * @return boolean - можно или нет считать экземпляр валидным
   */
  @Override
  public boolean valid() {
    return super.valid() & country != null && !country.isBlank();
  }
}
