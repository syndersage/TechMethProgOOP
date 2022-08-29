package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Aphorism extends Wisdom {
    private String author;

    /***
     * В экземпляр построчно передается иноформация из переданного источника об авторе и афоризме
     *
     * @param scan источник иноформации
     */
    @Override
    public void in(Scanner scan) {
        try {
            this.author = scan.nextLine();
            inText(scan);
        } catch (NoSuchElementException e) {
            System.out.println("Cannot read wisdom: end of file");
        } catch (NumberFormatException e) {
            System.out.println("Incorrect wisdom rate!");
        }
    }

    /***
     * Выводит информацию об афоризме и авторе
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Aphorism: " + text + ". By: " + author + ".");
    }

    /***
     * Экземпляр афоризмы проверяется на то, что текст и автор не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return text != null && !text.isBlank() & author != null && !author.isBlank();
    }
}
