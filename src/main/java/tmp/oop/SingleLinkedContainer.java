package tmp.oop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SingleLinkedContainer {
    //Размер списка
    private int size;
    //Первый элемент
    private Node head;
    //Последний элемент
    private Node tail;

    /***
     * Чтение из источника информации о мудростях, и запись их в список
     *
     * @param scan источник для чтения
     * @throws IllegalStateException не найден введенный тип мудрости
     * @throws IOException при проблемах с чтением из источника
     */
    public void in(Scanner scan) throws IllegalStateException, IOException {
        while (scan.hasNextLine()) {
            int typeNumber = 0;
            try {
                typeNumber = Integer.parseInt(scan.nextLine()) - 1;
                NodeType type = NodeType.values()[typeNumber];
                Wisdom wisdom;
                switch (type) {
                    //Создание экземпляра указанного типа мудрости
                    case PROVERB -> wisdom = new Proverb();
                    case APHORISM -> wisdom = new Aphorism();
                    default -> throw new IllegalStateException("Unexpected value: " + type);
                }
                wisdom.in(scan);
                if (wisdom.valid()) {
                    //Добавление мудрости в список только если она валидна
                    Node newNode = new Node(wisdom, null);
                    if (tail != null) tail.next = newNode;
                    if (head == null) head = newNode;
                    tail = newNode;
                    size++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Введено несуществующее значение типа мудрости
                //Пропускаются 2 последующие строчки из-за невозможности определния типа
                System.out.println("Wisdom skipped: non-existent wisdom type: " + (typeNumber + 1));
                if (scan.hasNextLine()) scan.nextLine();
                else return;
                if (scan.hasNextLine()) scan.nextLine();
                else return;
            } catch (NumberFormatException e) {
                //Вместо числового типа мудрости указан другой текст
                while (scan.hasNextLine() && !scan.hasNextInt()) scan.nextLine();
                if (!scan.hasNextLine()) return;
            }
        }
    }

    /***
     * Вывод информации о всем списке при помощи вызова собственных элементов вывода мудростей
     *
     * @param pw источник для записи
     */
    public void out(PrintWriter pw) {
        Node temp = head;
        for (int i = 1; i <= size; i++) {
            pw.print(i + ": ");
            temp.wisdom.out(pw);
            temp = temp.next;
        }
    }

    /***
     * Очистка списка
     */
    public void clear() {
        size = 0;
        tail = null;
        head = null;
    }


    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    /***
     * Типы мудростей
     */
    enum NodeType {
        APHORISM, PROVERB
    }

    static class Node {
        Wisdom wisdom;
        Node next;

        /***
         * Единственный конструктор для создания элемента списка
         * @param wisdom мудрость
         * @param next следующий элемент списка
         */
        public Node(Wisdom wisdom, Node next) {
            this.wisdom = wisdom;
            this.next = next;
        }
    }
}
