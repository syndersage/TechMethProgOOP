package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Proverb extends Wisdom {
    //Страна
    private String country;

    /***
     * В экземпляр построчно заносится иноформация из переданного источника о стране и пословице
     *
     * @param scan источник иноформации
     */
    @Override
    public void in(Scanner scan) {
        try {
            this.country = scan.nextLine();
            super.in(scan);
        } catch (NoSuchElementException e) {
            if (Client.verbose) Client.logOut.print("Cannot read wisdom: end of file. ");
        } catch (NumberFormatException e) {
            if (Client.verbose) Client.logOut.print("Incorrect rating input: Expected: [1 ... 10]. ");
        }
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

    /***
     * Выводит информацию о пословице и сране происхождения
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Proverb: " + getText() + ". From: " + country + ". Rating score: " + getRate());
    }

    /***
     * Экземпляр пословицы проверяется на валидность родительского метода и то что поле страна не null и не пустое
     * Экземпляр пословицы проверяется на то, что текст или страна не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return super.valid() & country != null && !country.isBlank();
    }
}
