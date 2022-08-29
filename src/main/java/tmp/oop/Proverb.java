package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Proverb extends Wisdom {
    //Страна
    private String country;

    /***
     * В экземпляр построчно передается иноформация из переданного источника о стране и пословице
     *
     * @param scan источник иноформации
     */
    @Override
    public void in(Scanner scan) {
        try {
            this.country = scan.nextLine();
            inText(scan);
        } catch (NoSuchElementException e) {
            System.out.println("Cannot read wisdom: end of file");
        }
    }

    /***
     * Выводит информацию о пословице и сране происхождения
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Proverb: " + text + ". From: " + country + ".");
    }

    /***
     * Экземпляр пословицы проверяется на то, что текст и страна не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return text != null && !text.isBlank() & country != null && !text.isBlank();
    }
}
