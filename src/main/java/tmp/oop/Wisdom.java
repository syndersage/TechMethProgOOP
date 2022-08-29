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

    /***
     * Подсчет количества знаков припинания у мудрости (поля text)
     *
     * @return целое число - кол-во знаков препинания
     */
    long countPunctuationMarks() {
        //Строка из символов, которые будут восприниматься как знаки препинания
        String punMarks = "!.,:;'-?\"";
        return text.chars().filter((x) -> punMarks.indexOf(x) != -1).count(); //Удаление из потока всех символов, не входящими в строку punMarks и вывод их количества
    }
}
