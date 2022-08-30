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
            String typeNumber = "-1"; //Строка для чтения типа мудрости
            try {
                typeNumber = scan.nextLine();
                Wisdom.NodeType type = Wisdom.NodeType.values()[Integer.parseInt(typeNumber) - 1];
                Wisdom wisdom;
                switch (type) {
                    //Создание экземпляра указанного типа мудрости
                    case PROVERB -> wisdom = new Proverb();
                    case APHORISM -> wisdom = new Aphorism();
                    case RIDDLE -> wisdom = new Riddle();
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
                    System.out.println("+Wisdom");
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                //Введено несуществующее значение типа мудрости
                //Пропускаются 3 последующие строчки из-за невозможности определния типа
                System.out.println("Wisdom skipped: non-existent wisdom type: " + typeNumber);
                for (int i = 0; i < 3; i++) {
                    if (scan.hasNextLine()) scan.nextLine();
                    else return;
                }
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

    /***
     * Сортировка списка методом вставки (у Node меняются значения Wisdom)
     */
    public void sort() {
        int j;
        for (int i = 1; i < size; i++) {
            j = i;
            while (j > 0) {
                Node n1 = getNode(j);
                Node n2 = getNode(j - 1);
                if (n1 == null | n2 == null) return;
                if (n1.wisdom.compareTo(n2.wisdom) > 0) break;
                Wisdom temp = n1.wisdom;
                n1.wisdom = n2.wisdom;
                n2.wisdom = temp;
                j--;
            }
        }
    }

    /***
     * Вспомогательный метод для поиска элемента с указанным порядковым номером.
     * Производится проверка на корректность переданного в параметрах индекса
     *
     * @param index порядковый номер элемента из списка
     * @return элемент тип Node в случае если порядковый номер (индекс) больше 0 и меньше размера списка, иначе null
     */
    private Node getNode(int index) {
        if (index < 0 | index > size - 1) return null;
        Node node = this.head;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
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
