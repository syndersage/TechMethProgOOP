package tmp.oop;

import java.io.PrintWriter;
import java.util.Scanner;

abstract class Wisdom implements Comparable<Wisdom> {
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
    /***
     * Сравнивает две мудрости. Для сравнения используется величина рейтинга (поля rate)
     *
     * @param w1 мудрость 1
     * @param w2 мудрость 2
     * @return 0 если w1 == w2, меньше 0 если w1 < w2, больше 0 если w1 > w2
     */
    static int compare(Wisdom w1, Wisdom w2) {
        return (int) (w1.countPunctuationMarks() - w2.countPunctuationMarks());
    }

    @Override
    public int compareTo(Wisdom o) {
        return compare(this, o);
    }
}
