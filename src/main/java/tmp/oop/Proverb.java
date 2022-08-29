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
            inRate(scan);
        } catch (NoSuchElementException e) {
            System.out.println("Cannot read wisdom: end of file");
        } catch (NumberFormatException e) {
            System.out.println("Wisdom skipped: Incorrect rating input. Expected: 1 - 10");
        }
    }

    /***
     * Выводит информацию о пословице и сране происхождения
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Proverb: " + text + ". From: " + country + ". Rating score: " + rate);
    }

    /***
     * Экземпляр пословицы проверяется на валидность родительского метода и то что поле страна не null и не пустое
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return super.valid() & country != null && !text.isBlank();
    }
}
