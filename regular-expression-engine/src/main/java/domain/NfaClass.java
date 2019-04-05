package domain;

import datastructure.StateList;

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
    @Override
    public State getStart() {
        return start;
    }

    /**
     * Return Nfa's end state
     *
     * @return state where Nfa ends
     */
    @Override
    public State getEnd() {
        return end;
    }

    /**
     * Search is used to check if Nfa's language recognizes given word
     *
     * @param word that is checked
     * @return true if word belongs to Nfa's language, otherwise false
     */
    @Override
    public boolean search(String word) {
        StateList currentStates = new StateList();
        currentStates = addNextState(this.getStart(), currentStates, new StateList());

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            StateList nextStates = new StateList();

            for (State state : currentStates.getStates()) {
                if (state.acceptsSymbol(symbol)) {
                    State nextState = state.getSymbolTransition().getTo();
                    nextStates = addNextState(nextState, nextStates, new StateList());
                }
            }
            currentStates = nextStates;
        }
        for (State state : currentStates.getStates()) {
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
    private StateList addNextState(State state, StateList nextStates, StateList visited) {
        if (state.hasEpsilonTransitions()) {
            for (EpsilonTransition etr : state.getEpsilonTransitions()) {
                if (etr != null && !visited.contains(etr.getTo())) {
                    visited.add(etr.getTo());
                    addNextState(etr.getTo(), nextStates, visited);
                }
            }
        } else {
            nextStates.add(state);
        }
        return nextStates;
    }
}
