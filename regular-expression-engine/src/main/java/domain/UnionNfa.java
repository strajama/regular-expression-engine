package domain;

/**
 * UnionNfa implements union between two given Nfas and creates new Nfa that
 * recognizes word that is recognixed by either one of given Nfas..
 *
 * @author strajama
 */
public class UnionNfa extends NfaClass {

    /**
     * Creates new UnionNfa from two given Nfas. It has new beginning and end
     * state and changes given Nfas old end states to be not accepting. UnionNfa
     * has four epsilon transitions: 1-2) from new beginning state to the given
     * Nfas beginning states so that UnionNfa recognizes word from either one of
     * them. 3-4) from the given Nfas end states to new end state so that
     * UnionNfa that word that is recognized by one of the given Nfas is also
     * recognized by UnionNfa.
     *
     * @param first - Nfa that is one part of union.
     * @param second - Nfa that is another part of union.
     */
    public UnionNfa(Nfa first, Nfa second) {
        this.start = new State(false);
        this.start.addTransition(new EpsilonTransition(this.start, first.getStart()));
        this.start.addTransition(new EpsilonTransition(this.start, second.getStart()));
        this.end = new State(true);
        first.getEnd().addTransition(new EpsilonTransition(first.getEnd(), this.end));
        first.getEnd().setIsEnd(false);
        second.getEnd().addTransition(new EpsilonTransition(second.getEnd(), this.end));
        second.getEnd().setIsEnd(false);
    }
}
