package tmp.oop;

import java.io.PrintWriter;
import java.util.Scanner;

public interface Wisdom {
    //Заполнение полей мудрости
    void in(Scanner scan);
    //Вывод информации о полях мудрости
    void out(PrintWriter pw);
    //Проверка мудрости на корректность
    boolean valid();
}
