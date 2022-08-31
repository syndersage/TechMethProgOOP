package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Aphorism extends Wisdom {
    //Автор афоризмы
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
            super.in(scan);
        } catch (NoSuchElementException e) {
            if (Client.verbose) Client.logOut.print("Cannot read wisdom: end of file. ");
        } catch (NumberFormatException e) {
            if (Client.verbose) Client.logOut.print("Wisdom skipped: Incorrect rating input. Expected: [1 ... 10]. ");
        }
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

    /***
     * Выводит информацию об афоризме и авторе
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Aphorism: " + getText() + ". By: " + author + ". Rating score: " + getRate());
    }

    /***
     * Экземпляр афоризмы проверяется на валидность родительского метода и то что поле автор не null и не пустое
     * Экземпляр афоризмы проверяется на то, что текст или автор не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return super.valid() & author != null && !author.isBlank();
    }
}
