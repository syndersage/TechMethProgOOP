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
    void in(Scanner scan) {
        try {
            this.answer = scan.nextLine();
            this.text = scan.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Cannot read wisdom: end of file");
        }
    }

    /***
     * Выводит информацию о загадке и ответе на неё
     * @param pw ресурс для вывода информации
     */
    @Override
    void out(PrintWriter pw) {
        pw.println("Riddle: " + text + ". Answer: " + answer + ".");
    }

    /***
     * Экземпляр загадки проверяется на то, что текст или ответ не null и не пустые
     *
     * @return boolean - можно или нет считать экземпляр валидным
     */
    @Override
    boolean valid() {
        return text != null && !text.isBlank() & answer != null && !answer.isBlank();
    }
}
