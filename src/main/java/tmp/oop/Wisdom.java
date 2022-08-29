package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

abstract class Wisdom {
    //Текст мудрости
    String text;

    //Субъективная оценка мудрости
    byte rate;

    /***
     * Типы мудростей
     */
    enum NodeType {
        APHORISM, PROVERB
    }
    //Заполнение полей мудрости
    abstract void in(Scanner scan);
    void inText(Scanner scan) throws NoSuchElementException {
        this.text = scan.nextLine();
    }
    //Вывод информации о полях мудрости
    abstract void out(PrintWriter pw);
    //Проверка мудрости на корректность
    abstract boolean valid();
}
