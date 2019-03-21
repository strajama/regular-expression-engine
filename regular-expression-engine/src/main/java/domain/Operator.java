package domain;

/**
 * Operator-enum stores the information about different kinds of operators in
 * language.
 *
 * @author strajama
 */
public enum Operator {
    VERTICALBAR('|', 0),
    INTERPUNCT('·', 1),
    ASTERISK('*', 2);

    private char symbol;
    private int precedence;

    /**
     * Creates new Operator-
     *
     * @param symbol - Operators symbol character
     * @param precedence - Operators precedence
     */
    Operator(char symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    /**
     * Returns Operators symbol-character
     *
     * @return symbol of the Operator
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns Operators precedence
     *
     * @return precedence of the Operator
     */
    public int getPrecedence() {
        return precedence;
    }

}
