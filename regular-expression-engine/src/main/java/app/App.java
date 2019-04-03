package app;

import domain.Matcher;
import domain.Postfix;

/**
 * App controls application
 *
 * @author strajama
 */
public class App {

    private IO io;

    /**
     * Inits App
     *
     * @param io - IO that the App is using
     */
    public void init(IO io) {
        this.io = io;
    }

    /**
     * Runs App
     *
     * @throws Exception
     */
    public void run() throws Exception {

        boolean quit = false;

        while (!quit) {
            String command = io.readLine(Order.COMMANDS.getPrinting());

            switch (command) {
                case "matcher":
                    String language = io.readLine(Order.LANGUAGE.getPrinting());
                    Matcher matcher = new Matcher(language);
                    String word = io.readLine(Order.WORD.getPrinting());
                    if (matcher.wordMatches(word)) {
                        System.out.println("word belongs to language");
                    } else {
                        System.out.println("word doesn't belong to language");
                    }
                    break;
                case "postfix":
                    String infix = io.readLine(Order.POSTFIX.getPrinting());
                    Postfix postfix = new Postfix(infix);
                    System.out.println("Expression is " + postfix.toString());

                case "quit":
                    System.out.println("Thank you for using regular expression engine!");
                    quit = true;
            }
        }
    }
}
