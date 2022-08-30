package tmp.oop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {
    public static boolean verbose = false;
    public static PrintStream logOut = System.out;
    public static void main(String[] args) {
        if (args.length == 3 && args[2].equals("-v")) verbose = true;
        else {
            if (args.length != 2) { //Проверка на количество параметров запуска
                System.out.println("Incorrect parameters. Expected: input_file output_file verbose(optional)" + " Received: " + Arrays.stream(args).map(Objects::toString).collect(Collectors.joining(" ")));
                return;
            }
        }
        String inputFilePath = Path.of(args[0]).isAbsolute() ? args[0] :     //Если параметр относительный путь, то
                Path.of(System.getProperty("user.dir"), args[0]).toString(); //указанный путь добавляется к рабочей директории
        String outputFilePath = Path.of(args[1]).isAbsolute() ? args[1] :
                Path.of(System.getProperty("user.dir"), args[1]).toString();
        SingleLinkedContainer slc = new SingleLinkedContainer(); //Список в котором будут храниться мудрости
        try(Scanner scan = new Scanner(Path.of(inputFilePath), StandardCharsets.UTF_8);   //Получение абсолютного пути к файлу чтения из 1 параметра
            PrintWriter pw = new PrintWriter(outputFilePath)) { //Получение абсолютного пути к файлу записи из 2 параметра
            slc.in(scan);
            pw.println("Filled container.\r\nContainer contains " + slc.getSize() + " elements.");
            slc.sort();
            slc.out(pw);
            slc.clear();
            pw.println("Empty container.\r\nContainer contains " + slc.getSize() + " elements.");
        }
        catch (FileNotFoundException | NoSuchFileException e) { //Если источник для записи/чтения не найден/недоступен
            if (verbose) logOut.println("File not found: " + e.getMessage());
        }
        catch (IOException e) { //Прочие ошибки чтения и записи
            if (verbose) logOut.println("Input/Output error: " + e.getMessage());
        }
    }
}
