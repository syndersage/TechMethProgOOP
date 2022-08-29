package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

abstract class Wisdom {
    //Текст мудрости
    String text;

    //Оценка мудрости
    byte rate;

    //Типы мудростей
    enum NodeType {
        APHORISM, PROVERB
    }
    //Заполнение полей мудрости
    abstract void in(Scanner scan);

    /***
     * Чтение текста мудрости и запись в соответствующее поле
     *
     * @param scan источник данных
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    void inText(Scanner scan) throws NoSuchElementException {
        this.text = scan.nextLine();
    }

    /***
     * Чтение рейтинга и запись в соответствующее поле в соответствии с допустимыми значениями
     *
     * @param scanner источник данных
     * @throws NumberFormatException если в строке указано не byte значение, либо величина не соответствует требуемым значениям
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    void inRate(Scanner scanner) throws NumberFormatException, NoSuchElementException {
        rate = 0; //По-умолчанию значение 0 - оценка отсутствует
        this.rate = Byte.parseByte(scanner.nextLine());
        if (rate < 1 | rate > 10) {
            rate = 0;
            throw new NumberFormatException();
        }
    }

    //Вывод информации о полях мудрости
    abstract void out(PrintWriter pw);

    /***
     * Проверка мудрости на корректность - текста и рейтинга
     *
     * @return является ли экземпляр корректным
     */
    boolean valid() {
        return text != null && !text.isBlank() & rate != 0;
    }
}
