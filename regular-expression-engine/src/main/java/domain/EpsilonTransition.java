package domain;

/**
 * EpsilonTransition implements interface Transition
 *
 * @author strajama
 */
public class EpsilonTransition implements Transition {

    private State from;
    private State to;

    /**
     * Creates new EpsilonTransition from one given state to another.
     *
     * @param from - state where the transition begins
     * @param to - state where transition ends
     */
    public EpsilonTransition(State from, State to) {
        this.from = from;
        this.to = to;
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
     * Gives the information if state is symbol transition
     *
     * @return false
     */
    public boolean hasSymbol() {
        return false;
    }
}
