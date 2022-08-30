package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Riddle extends Wisdom {
    //Ответ на загадку
    private String answer;

    /***
     * В экземпляр построчно передается иноформация из переданного источника о загадке и ответе на неё
     *
     * @param scan источник иноформации
     */
    @Override
    public void in(Scanner scan) {
        try {
            this.answer = scan.nextLine();
            super.in(scan);
        } catch (NoSuchElementException e) {
            if (Client.verbose) Client.logOut.print("Cannot read wisdom: end of file. ");
        } catch (NumberFormatException e) {
            if (Client.verbose) Client.logOut.print("Wisdom skipped: Incorrect rating input. Expected: [1 ... 10]. ");
        }
    }

    /***
     * Выводит информацию о загадке и ответе на неё
     * @param pw ресурс для вывода информации
     */
    @Override
    public void out(PrintWriter pw) {
        pw.println("Riddle: " + getText() + ". Answer: " + answer + ". Rating score: " + getRate());
    }

    /***
     * Экземпляр загадки проверяется на то, что текст или ответ не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    public boolean valid() {
        return super.valid() & answer != null && !answer.isBlank();
    }
}
