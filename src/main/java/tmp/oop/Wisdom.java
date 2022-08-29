package tmp.oop;

import java.io.PrintWriter;
import java.util.Scanner;

abstract class Wisdom {
    //Текст мудрости
    String text;
    /***
     * Типы мудростей
     */
    enum NodeType {
        APHORISM, PROVERB
    }
    //Заполнение полей мудрости
    abstract void in(Scanner scan);
    //Вывод информации о полях мудрости
    abstract void out(PrintWriter pw);
    //Проверка мудрости на корректность
    abstract boolean valid();
}
