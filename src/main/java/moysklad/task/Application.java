package moysklad.task;

import java.io.File;
import java.util.Scanner;

/**
 * Main class for application
 */
public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file;

        do {
            System.out.println("Enter a path to file with data:");
            scanner.hasNextLine();
            String path = scanner.nextLine();

            file = new File(path);
        }
        while (!file.exists());

        LoopFinder finder = new LoopFinder(file);
        finder.findLoops();
    }
}
