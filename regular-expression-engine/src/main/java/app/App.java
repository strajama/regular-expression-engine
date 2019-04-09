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
        int counter = 0;

        while (!quit) {
            String command = io.readLine(Order.COMMANDS.getPrinting());

            switch (command) {
                case "matcher":
                    String language = io.readLine(Order.LANGUAGE.getPrinting());
                    long aikaAlussa = System.currentTimeMillis();
                    Matcher matcher = new Matcher(language);
                    long matcherAika = System.currentTimeMillis();
                    String word = "";
                    counter = 0;
                    do {
                        word = io.readLine(Order.WORD.getPrinting());
                        if (matcher.wordMatches(word)) {
                            counter ++;
                            System.out.println(word);
                        } else {
                            System.out.println("");
                        }
                    } while (!word.equals("/no"));
                    long aikaLopussa = System.currentTimeMillis();
                    System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
                    System.out.println("Matcherin rakentamiseen kului aikaa: " + (matcherAika - aikaAlussa) + "ms.");
                    System.out.println("You printed "+counter+" words.");
                    break;
                case "postfix":
                    String infix = io.readLine(Order.POSTFIX.getPrinting());
                    Postfix postfix = new Postfix(infix);
                    System.out.println("Expression is " + postfix.toString());
                    break;

                case "quit":
                    System.out.println("Thank you for using regular expression engine!");
                    quit = true;
                    break;
            }
        }
    }
}
