package domain;

/**
 * Matcher puts everything together.
 *
 * @author strajama
 */
public class Matcher {

    private String postfixExp;
    private Nfa nfa;

    /**
     * Creates new Matcher from given expression. Expression is changed by new
     * Postfix to a proper form so that a new BuilderNfa can be build from it.
     *
     * @param expression - language that we want to recognize
     */
    public Matcher(String expression) {
        this.postfixExp = new Postfix(expression).toString();
        this.nfa = new BuilderNfa(postfixExp);
    }

    /**
     * Tells if given word belongs to language that Matcher recognizes. It
     * calls a search-method from Matchers nfa and with the given word.
     *
     * @param word - word that is checked
     * @return true if word belongs to Matchers language and false if it doesn't
     */
    public boolean wordMatches(String word) {
        return this.nfa.search(word);
    }

}
