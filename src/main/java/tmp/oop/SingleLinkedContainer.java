package tmp.oop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SingleLinkedContainer {

  //Размер списка
  private int size;
  //Первый элемент
  private Node head;
  //Последний элемент
  private Node tail;


  /**
   * Чтение из источника информации о мудростях, и запись их в список
   *
   * @param scan источник для чтения
   * @throws IllegalStateException не найден введенный тип мудрости
   * @throws IOException           при проблемах с чтением из источника
   */
  public void in(Scanner scan) throws IllegalStateException, IOException {
    while (scan.hasNextLine()) {
      try {
        Wisdom wisdom;
        wisdom = Wisdom.in(scan);
        if (wisdom != null) {
          //Добавление мудрости в список только если она валидна
          Node newNode = new Node(wisdom, null);
          if (tail != null) {
            tail.next = newNode;
          }
          if (head == null) {
            head = newNode;
          }
          tail = newNode;
          size++;
          if (Client.arguments.verbose) {
            Client.LOG_OUT.println("+Wisdom");
          }
        }
      } catch (NumberFormatException | NoSuchElementException e) {
        //Введено несуществующее значение типа мудрости
        //пропускаются numberOfWisdomFields последующие строчки из-за невозможности определения типа
        if (Client.arguments.verbose) {
          Client.LOG_OUT.println(e.getMessage());
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

  /**
   * Вывод информации только о первом типе мудростей (афоризмов) при помощи вызова собственных элементов вывода мудростей
   *
   * @param pw источник для записи информации о мудростях
   */
  public void outFirstType(PrintWriter pw) {
    Node temp = head;
    if (temp == null || temp.wisdom == null) {
      return;
    }
    for (int i = 1; i <= size; i++) {
      if (temp.wisdom instanceof Aphorism) {
        pw.print(i + ": ");
        temp.wisdom.out(pw);
      }
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
        if (n1 == null | n2 == null) {
          return;
        }
        if (n1.wisdom.compareTo(n2.wisdom) >= 0) {
          break;
        }
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
    if (index < 0 | index > size - 1) {
      return null;
    }
    Node node = this.head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node;
  }

  /**
   * Перебирает каждые 2 элемента из контейнера и выводит их типы
   *
   * @param pw источник для вывода информации о типах
   */
  public void iterateEveryPair(PrintWriter pw) {
    Node firstNode, secondNode;
    for (int i = 0; i < size - 1; i++) {
      firstNode = getNode(i);
      for (int j = i + 1; j < size; j++) {
        secondNode = getNode(j);
        if (firstNode != null & secondNode != null) {
          firstNode.wisdom.inPairWith(secondNode.wisdom, pw);
        }
      }
    }
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
