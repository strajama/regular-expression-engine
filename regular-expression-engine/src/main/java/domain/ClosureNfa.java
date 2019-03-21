package domain;

/**
 * ClosureNfa is impelentation for Kleenee Star and creates Nfa that recognizes
 * language that has none or many words recognized by Nfa that is given when
 * ClosureNfa is created.
 *
 * @author strajama
 */
public class ClosureNfa extends NfaClass {

    /**
     * Creates new ClosureNfa from given Nfa. ClosureNfa has new beginning and
     * end state and Nfa's end state is changed to not accepting state. It has
     * four epsilon transitions: 1) from beginning to end state so that
     * ClosureNfa recognizes word with no words from the language from given
     * Nfa; 2) from beginning to old beginning state so that given Nfa can be
     * used to recognize word; 3) from old end state to new end state so that
     * ClosureNfa recognizes words that are recognized by given Nfa; 4) from
     * Nfa's old end state to it's old beginnig end so that ClosureNfa
     * recognizes word with many words from the language from given Nfa.
     *
     * @param nfa - that recognizes language
     */
    public ClosureNfa(Nfa nfa) {
        this.start = new State(false);
        this.end = new State(true);
        this.start.addTransition(new EpsilonTransition(this.start, this.end));
        this.start.addTransition(new EpsilonTransition(this.start, nfa.getStart()));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), this.end));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), nfa.getStart()));
        nfa.getEnd().setIsEnd(false);
    }
}
