package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * NfaClass is abstract class to store Nfas common methods.
 *
 * @author strajama
 */
public abstract class NfaClass implements Nfa {

    protected State start;
    protected State end;

    /**
     * Returns Nfa's beginning state
     *
     * @return state where Nfa begins
     */
    public State getStart() {
        return start;
    }

    /**
     * Return Nfa's end state
     *
     * @return state where Nfa ends
     */
    public State getEnd() {
        return end;
    }

    /**
     * Search is used to check if Nfa's language recognizes given word
     *
     * @param word that is checked
     * @return true if word belongs to Nfa's language, otherwise false
     */
    public boolean search(String word) {
        List<State> currentStates = new ArrayList<>();
        currentStates = addNextState(this.getStart(), currentStates, new ArrayList<>());

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            List<State> nextStates = new ArrayList<>();

            for (State state : currentStates) {
                if (state.hasSymbolTransition() && state.getSymbolTransition().getSymbol() == symbol) {
                    State nextState = state.getSymbolTransition().getTo();
                    nextStates = addNextState(nextState, nextStates, new ArrayList<>());
                }
            }
            currentStates = nextStates;
        }
        for (State state : currentStates) {
            if (state.getIsEnd()) {
                return true;
            }
        }
        return false;
    }

    /**
     * AddNextState is recursive procedure to deal with Nfa's epsilon
     * transitions.
     *
     * @param state that has epsilon transitions
     * @param nextStates is a list of states that there is transition to
     * @param visited is list of visited states to prevent infinite looping
     * @return list of states that can be reached
     */
    private List<State> addNextState(State state, List<State> nextStates, List<State> visited) {
        if (state.hasEpsilonTransitions()) {
            for (EpsilonTransition etr : state.getEpsilonTransitions()) {
                if (etr != null && !visited.contains(etr.getTo())) {
                    visited.add(etr.getTo());
                    addNextState(etr.getTo(), nextStates, visited);
                }
            }
//        } else if (state.hasSymbolTransition()) {
//            nextStates.add(state);
        } else {
            nextStates.add(state);
        }
        return nextStates;
    }
}