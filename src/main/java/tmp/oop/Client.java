package tmp.oop;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Главный класс программы, содержащий метод main.
 * Описывает процедуры открытия/закрытия файлов, а также проведения операций над информацией в них в зависимости от указанных аргументов
 */
public class Client {

  //Поток, в который будет осуществляться процесс работы программы (консоль)
  public final static PrintStream LOG_OUT = System.out;

  //Общедоступное поле, содержащее информацию обо всех входных параметрах программы
  public final static Args arguments = new Args();

  public static void main(String[] args) {
    JCommander jCmd = JCommander.newBuilder().addObject(arguments).build();
    jCmd.setProgramName("Client");
    try {
      jCmd.parse(args);
    } catch (ParameterException e) {
      System.out.println(e.getMessage());
      jCmd.usage();
      return;
    }
    if (arguments.help) {
      jCmd.usage();
      return;
    }
    SingleLinkedContainer slc = new SingleLinkedContainer(); //Список в котором будут храниться мудрости
    try (Scanner scan = new Scanner(arguments.inPath,
        StandardCharsets.UTF_8);   //Получение абсолютного пути к файлу чтения из 1 параметра
        PrintWriter pw = new PrintWriter(
            arguments.outPath.toString())) { //Получение абсолютного пути к файлу записи из 2 параметра
      slc.in(scan);
      pw.println("Filled container.\r\nContainer contains " + slc.getSize() + " elements.");
      if (arguments.sort) {
        slc.sort();
      }
      slc.out(pw);
      if (arguments.firstType) {
        pw.println("\r\nAphorisms:");
        slc.outFirstType(pw);
      }
      if (arguments.pair) {
        pw.println("\r\nIterating every pair:");
        slc.iterateEveryPair(pw);
      }
      slc.clear();
      pw.println("\r\nEmpty container.\r\nContainer contains " + slc.getSize() + " elements.");
    } catch (IOException e) { //Прочие ошибки чтения и записи
      if (arguments.verbose) {
        LOG_OUT.println("Input/Output error: " + e.getMessage());
      }
    }
  }
}
