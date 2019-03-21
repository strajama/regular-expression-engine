package domain;

/**
 * Interface for different kinds of transitions. Transitions have beginning and
 * end state and information if it has symbol or not.
 *
 * @author strajama
 */
public interface Transition {

    /**
     * Gives the information about transitions beginning state
     *
     * @return - state where the transition begins
     */
    State getFrom();

    /**
     * Gives the information about the transitions end state
     *
     * @return - state where the transition ends
     */
    State getTo();

    /**
     * Is used to tell if transition is symbol or epsilon transition
     *
     * @return true for symbol transitions and false for epsilon transitions
     */
    boolean hasSymbol();
}
