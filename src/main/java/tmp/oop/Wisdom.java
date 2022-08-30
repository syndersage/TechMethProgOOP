package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class Wisdom implements Comparable<Wisdom> {
    //Текст мудрости
    String text;

    //Оценка мудрости
    byte rate;

    //Типы мудростей
    enum NodeType {
        APHORISM, PROVERB, RIDDLE
    }
    //Заполнение полей мудрости
    public void in(Scanner scan) {
        inText(scan);
        inRate(scan);
    }

    /***
     * Чтение текста мудрости и запись в соответствующее поле
     *
     * @param scan источник данных
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    public void inText(Scanner scan) throws NoSuchElementException {
        this.text = scan.nextLine();
    }

    /***
     * Чтение рейтинга и запись в соответствующее поле в соответствии с допустимыми значениями
     *
     * @param scanner источник данных
     * @throws NumberFormatException если в строке указано не byte значение, либо величина не соответствует требуемым значениям
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    public void inRate(Scanner scanner) throws NumberFormatException, NoSuchElementException {
        rate = 0; //По-умолчанию значение 0 - оценка отсутствует
        this.rate = Byte.parseByte(scanner.nextLine());
        if (rate < 1 | rate > 10) {
            rate = 0;
            throw new NumberFormatException();
        }
    }

    //Вывод информации о полях мудрости
    public abstract void out(PrintWriter pw);


    /***
     * Проверка мудрости на корректность - текста и рейтинга
     *
     * @return является ли экземпляр корректным
     */
    public boolean valid() {
        return text != null && !text.isBlank() & rate != 0;
    }

    public String getText() {
        return text;
    }

    public byte getRate() {
        return rate;
    }

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
