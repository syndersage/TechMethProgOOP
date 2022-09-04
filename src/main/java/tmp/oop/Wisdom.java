package tmp.oop;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class Wisdom implements Comparable<Wisdom> {
    //Текст мудрости
    private String text;

    //Оценка мудрости
    private byte rate;

    //Абстрактный метод для чтения параметров, появляющихся у наследующих данный (Wisdom) класс
    public abstract void inData(Scanner scan) throws NoSuchElementException;

    /**
     * Статический метод для заполнения данных о мудрости в соответствии с её типом
     *
     * @param scan источник данных, описывающих мудрость
     * @return экземпляр мудрости
     * @throws NumberFormatException указан несоответствущий тип или оценка мудрости
     * @throws NoSuchElementException не удается прочитать строку, либо строка невалидна
     */
    public static Wisdom in(Scanner scan)
            throws NumberFormatException, NoSuchElementException {
        Wisdom wisdom;
        Type wisdomType;
        //Чтение строки для определения типа мудрости
        String line = scan.nextLine();
        if (line.isBlank()) {
            //Пустая строка типа данных воспринимается как разделитель между мудростями
            //возвращается null значение, способствующее переходу на следующую строку как на начало новой мудрости
            return null;
        } else {
            line = line.strip();
        }
        try {
            //Попытка парса числа (порядкового номера мудрости из нумерации) и вычисления типа мудрости
            wisdomType = Type.values()[Integer.parseInt(line) - 1];
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            //Пропускает 3 следующие строчки (которые должны были быть параметрами мудрости)
            //Работает только если количество параметров мудрости равно 3
            for (int i = 0; i < 3; i++) {
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }
            }
            //Если введено не число, либо оно выходит за установленные границы
            throw new NumberFormatException("Incorrect wisdom type. Expected: [1 - " +
                    (Type.values().length) + "]. Received: " + line);
        }
        //Создание экземпляра мудрости, в соответствии с введенным в прошлой строке типом
        switch (wisdomType) {
            case APHORISM -> wisdom = new Aphorism();
            case PROVERB -> wisdom = new Proverb();
            case RIDDLE -> wisdom = new Riddle();
            default -> wisdom = null;
        }
        if (wisdom != null) {
            //Чтение следующих numOfFields строк (их количество определяется типом мудрости)
            //После чего старый источник данных (Scanner) заменяется на новый
            //Данное дейсвтие связано с тем, что при ошибке в типе, следующая строка будет считаться
            //началом следующей мудрости илбо чего-то другого,
            //хотя изначально под неё выделялось определенное количество строк
            StringBuilder linesToRead = new StringBuilder();
            for (int i = 0; i < wisdomType.numOfFields; i++) {
                linesToRead.append(scan.nextLine()).append("\n");
            }
            scan = new Scanner(linesToRead.toString());
            //Заполнение полей мудрости
            wisdom.inData(scan);
            wisdom.inText(scan);
            wisdom.inRate(scan);
        }
        return wisdom;
    }

    /**
     * Чтение текста мудрости и запись в соответствующее поле
     *
     * @param scan источник данных
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    public void inText(Scanner scan) throws NoSuchElementException {
        String line = scan.nextLine();
        if (line.isBlank()) {
            //Если строка не соответствует требованиям (пустая)
            throw new NoSuchElementException("Wisdom text must be at least one symbol length.");
        }
        this.text = line;
    }

    /**
     * Чтение рейтинга и запись в соответствующее поле в соответствии с допустимыми значениями
     *
     * @param scan источник данных
     * @throws NumberFormatException если в строке указано не byte значение, либо величина не соответствует требуемым значениям
     * @throws NoSuchElementException если не удается прочитать строку (конец файла)
     */
    public void inRate(Scanner scan) throws NumberFormatException, NoSuchElementException {
        //Чтение строки с информацией об оценке
        String line = scan.nextLine();
        try {
            //Парсинг числа (значения оценки) из прочитанной строки
            rate = Byte.parseByte(line);
        } catch (NumberFormatException e) {
            //Если входные данные - не число
            throw new NumberFormatException("Incorrect rate input: Expected: [1 - 10]. Received: " + line);
        }
        //Проверка на соответствие установленным значениям оценки мудрости
        if (rate < 1 | rate > 10) {
            rate = 0;
            throw new NumberFormatException("Incorrect rate value: Expected: [1 - 10]. Received: " + line);
        }
    }

    /**
     * Абстрактный метод, в котором должны описываться действия для вывода пары мудростей
     * Тело метода будет зависеть от того, от какой мудрости вызван метод
     *
     * @param secondWisdom вторая мудрость, с которой первая должна состоять в паре
     * @param pw источник для записи информации о паре
     */
    public abstract void inPairWith(Wisdom secondWisdom, PrintWriter pw);

    /**
     * Абстрактный метод с описанием действий для вывода информации о паре мудростей,
     * где одна из мудростей Aphorism, вторая - та, из экземпляра которой был вызван метод
     *
     * @param pw источник для записи информации о паре
     */
    public abstract void inPairWithAphorism(PrintWriter pw);

    /**
     * Абстрактный метод с описанием действий для вывода информации о паре мудростей,
     * где одна из мудростей Proverb, вторая - та, из экземпляра которой был вызван метод
     *
     * @param pw источник для записи информации о паре
     */
    public abstract void inPairWithProverb(PrintWriter pw);

    /**
     * Абстрактный метод с описанием действий для вывода информации о паре мудростей,
     * где одна из мудростей Riddle, вторая - та, из экземпляра которой был вызван метод
     *
     * @param pw источник для записи информации о паре
     */
    public abstract void inPairWithRiddle(PrintWriter pw);

    //Вывод информации о полях мудрости
    public abstract void out(PrintWriter pw);


    /**
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

     /**
     * Подсчет количества знаков припинания у мудрости (поля text)
     *
     * @return целое число - кол-во знаков препинания
     */
    long countPunctuationMarks() {
        //Строка из символов, которые будут восприниматься как знаки препинания
        String punMarks = "!.,:;'-?\"";
        return text.chars().filter((x) -> punMarks.indexOf(x) != -1).count(); //Удаление из потока всех символов, не входящими в строку punMarks и вывод их количества
    }

    /**
     * Реализация метода, необходимого для наследования интерфейса Compareable,
     * позволяющего сравнивать 2 экземпляра данного класса по величине.
     * Для сравнения подсчитывается количество знаков препинания
     *
     * @param obj мудрость с которой будет производиться сравнение
     * @return 0 - this == obj, меньше 0 - this < obj, больше 0 - this > obj
     */
    @Override
    public int compareTo(Wisdom obj) {
        return (int) (this.countPunctuationMarks() - obj.countPunctuationMarks());
    }

    //Типы мудростей
    enum Type {
        APHORISM(3), PROVERB(3), RIDDLE(3);
        private final int numOfFields;

        Type(int numOfFields) {
            this.numOfFields = numOfFields;
        }
    }
}
