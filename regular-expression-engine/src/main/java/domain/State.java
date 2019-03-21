package domain;

/**
 * State-class has the information if it is a accepting state to the language.
 * Moving between states happens with transitions. State can have one symbol
 * transition or at most two epsilon transitions.
 *
 * @author strajama
 */
public class State {

    private SymbolTransition symbolTransition;
    private EpsilonTransition[] epsilonTransitions;
    private boolean isEnd;

    /**
     * Creates new State. State has null value in symbol transition and null
     * array for two epsilon transitions
     *
     * @param isEnd - information if the state is accepting state
     */
    public State(boolean isEnd) {
        this.isEnd = isEnd;
        this.epsilonTransitions = new EpsilonTransition[2];
        this.symbolTransition = null;
    }

    /**
     * Returns states symbol transition if it has one
     *
     * @return symbol transition or null
     */
    public SymbolTransition getSymbolTransition() {
        return symbolTransition;
    }

    /**
     * Return array size of two with epsilon transitions
     *
     * @return array with nulls or one or two epsilon transitions
     */

    public EpsilonTransition[] getEpsilonTransitions() {
        return epsilonTransitions;
    }

    /**
     * Gives the information if the State is accepting state
     *
     * @return true if state is accepting state
     */
    public boolean getIsEnd() {
        return isEnd;
    }

    /**
     * Changes states isEnd-value to a given value
     *
     * @param isEnd - information if state is accepting state or not
     */
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * Way to check if state has symbol transition
     *
     * @return true if state has symbol transition
     */
    public boolean hasSymbolTransition() {
        return this.symbolTransition != null;
    }

    /**
     * Way to check if state has epsilon transitions
     *
     * @return true if state has epsilon transitions
     */
    public boolean hasEpsilonTransitions() {
        return this.epsilonTransitions[0] != null;
    }

    /**
     * Adds a new transition to state. If given transition is symbol transition
     * and state has no epsilon transition, symbol transition is added. If
     * transition is epsilon transition and state doesn't have two epsilon
     * transition, new epsilon transition is added to state.
     *
     * @param transition - can be symbol or epsilon transition
     */
    public void addTransition(Transition transition) {
        if (transition.hasSymbol() && !hasEpsilonTransitions()) {
            this.symbolTransition = (SymbolTransition) transition;
        } else if (!transition.hasSymbol()) {
            if (this.epsilonTransitions[0] == null) {
                this.epsilonTransitions[0] = (EpsilonTransition) transition;
            } else if (this.epsilonTransitions[1] == null) {
                this.epsilonTransitions[1] = (EpsilonTransition) transition;
            }
        }
    }

    /**
     * Tells how many epsilon transitions state has
     *
     * @return 0, 1 or 2
     */
    public int numberOfEpsilons() {
        if (this.epsilonTransitions[0] == null) {
            return 0;
        } else if (this.epsilonTransitions[1] == null) {
            return 1;
        }
        return 2;
    }
}
