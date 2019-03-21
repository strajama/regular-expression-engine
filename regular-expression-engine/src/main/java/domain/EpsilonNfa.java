package domain;

/**
 * EpsilonNfa is basic building block for more complex Nfa's with epsilon
 * transition or transitions.
 *
 * @author strajama
 */
public class EpsilonNfa extends NfaClass {

    /**
     * Creates new EpsilonNfa that has beginning and end state and epsilon
     * transition between them.
     */
    public EpsilonNfa() {
        this.start = new State(false);
        this.end = new State(true);
        this.start.addTransition(new EpsilonTransition(this.start, this.end));
    }
}
