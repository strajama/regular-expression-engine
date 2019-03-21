package domain;

/**
 * ConcatNfa implements concatenation between two different given Nfas.
 *
 * @author strajama
 */
public class ConcatNfa extends NfaClass {

    /**
     * Creates new ConcatNfa from two given Nfas. It creates epsilon transition
     * from first Nfa's end state to second Nfa's beginning state and changes
     * first Nfa's accepting state from accepting to not accepting. ConcatNfa's
     * beginning state is first Nfa's beginning state and end state is second
     * Nfa's end state.
     *
     * @param first - Nfa that is the first part of concatenation.
     * @param second - Nfa that is the second part of concatenation.
     */
    public ConcatNfa(Nfa first, Nfa second) {
        first.getEnd().addTransition(new EpsilonTransition(first.getEnd(), second.getStart()));
        first.getEnd().setIsEnd(false);
        this.start = first.getStart();
        this.end = second.getEnd();
    }
}
