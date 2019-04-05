package app;

import java.util.Scanner;

/**
 * ConsoleIO implement IO-interface and is given to App when application is
 * used in console.
 *
 * @author strajama
 */
public class ConsoleIO implements IO {

    private Scanner input;

    /**
     * Creates new ConsoleIO
     *
     * @throws ClassNotFoundException
     */
    public ConsoleIO() throws ClassNotFoundException {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads line from the user
     *
     * @param line - Order-enums printing as a String
     * @return - line that the user has written to the console
     */
    @Override
    public String readLine(String line) {
        System.out.println(line);
        return input.nextLine();
    }
}
