package domain;

/**
 * Nfa-interface is for different kinds of Nondeterministic Finite Automata. Nfa
 * always starts from the one state and has one end state that is accepting. No
 * transitions happens to beginning state and no transitions happens from end
 * state.
 *
 * @author strajama
 */
public interface Nfa {

    /**
     * Returns Nfa's beginning state
     *
     * @return state where Nfa begins
     */
    State getStart();

    /**
     * Return Nfa's end state
     *
     * @return state where Nfa ends
     */
    State getEnd();

    /**
     * Search is used to check if Nfa's language recognizes given word
     *
     * @param word that is checked
     * @return true if word belongs to Nfa's language, otherwise false
     */
    boolean search(String word);
}
