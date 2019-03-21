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
     * Creates new State
     *
     * @param isEnd - information if the state is accepting state
     */
    public State(boolean isEnd) {
        this.isEnd = isEnd;
        this.epsilonTransitions = new EpsilonTransition[2];
        this.symbolTransition = null;
    }

    public SymbolTransition getSymbolTransition() {
        return symbolTransition;
    }

    public EpsilonTransition[] getEpsilonTransitions() {
        return epsilonTransitions;
    }

    /**
     * Gives the information if the State is accepting state
     *
     * @return
     */
    public boolean getIsEnd() {
        return isEnd;
    }

    /**
     * Changes states isEnd-value to a given value
     *
     * @param isEnd
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
 * @return true if state has epsilon transitions
 */
    public boolean hasEpsilonTransitions() {
        return this.epsilonTransitions[0] != null;
    }
/**
 * Adds a new transition to state
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

    public int numberOfEpsilons() {
        if (this.epsilonTransitions[0] == null) {
            return 0;
        } else if (this.epsilonTransitions[1] == null) {
            return 1;
        }
        return 2;
    }
}
