package app;

/**
 * Order-enum is used to give user instructions in the console
 *
 * @author strajama
 */
public enum Order {
    COMMANDS("Choose command: matcher, quit "),
    WORD("Give a word to check "),
    LANGUAGE("Give language ");

    private String printing;

    /**
     * Creates new Order
     *
     * @param printing - String that is printed to the console
     */
    private Order(String printing) {
        this.printing = printing;
    }

    /**
     * Returns Orders printing-String
     *
     * @return String
     */
    public String getPrinting() {
        return printing;
    }
}