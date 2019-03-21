package domain;

/**
 * SymbolTransition implements Transition-interface
 *
 * @author strajama
 */
public class SymbolTransition implements Transition {

    private State from;
    private State to;
    private char symbol;

    /**
     * Creates new SymbolTransition
     *
     * @param from - state where the transition begins
     * @param to - state where transition ends
     * @param symbol - symbol that is needed for using transition
     */
    public SymbolTransition(State from, State to, char symbol) {
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }

    /**
     * Gives the information about transitions beginning state
     *
     * @return - state where the transition begins
     */
    public State getFrom() {
        return from;
    }

    /**
     * Gives the information about the transitions end state
     *
     * @return - state where the transition ends
     */
    public State getTo() {
        return to;
    }

    /**
     * Gives the information which symbol that is needed for using transition
     *
     * @return symbol-character
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gives the information if state is symbol transition
     *
     * @return true
     */
    public boolean hasSymbol() {
        return true;
    }
}
