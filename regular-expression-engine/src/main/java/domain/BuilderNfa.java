package domain;

import datastructure.NfaStack;

/**
 * BuilderNfa constructs new Nfa from one or many Nfas.
 *
 * @author strajama
 */
public class BuilderNfa extends NfaClass {

    /**
     * Creates new BuilderNfa from given postfix expression. Expression is
     * scanned one symbol at a time and context is stored in a stack that
     * contain Nfas. When symbol is a character that is not a Operator, a new
     * SymbolNfa is created and pushed to the stack. When symbol is a Operator,
     * stack is popped and new Nfa is created from popped Nfas and pushed to the
     * stack. Final Nfa in the stack is the BuilderNfa.
     *
     * @param postfix - postfix expression for the language that BuilderNfa
     * recognizes.
     */
    public BuilderNfa(String postfix) {
        Nfa nfa = new EpsilonNfa();
        if ("".equals(postfix)) {
            this.start = nfa.getStart();
            this.end = nfa.getEnd();
        } else {
            NfaStack stack = new NfaStack();

            for (int i = 0; i < postfix.length(); i++) {
                char token = postfix.charAt(i);
                switch (token) {
                    case '*':
                        stack.push(new ClosureNfa(stack.pop()));
                        break;
                    case '|': {
                        Nfa right = stack.pop();
                        Nfa left = stack.pop();
                        stack.push(new UnionNfa(left, right));
                        break;
                    }
                    case '·': {
                        Nfa right = stack.pop();
                        Nfa left = stack.pop();
                        stack.push(new ConcatNfa(left, right));
                        break;
                    }
                    default:
                        stack.push(new SymbolNfa(token));
                        break;
                }
            }
            nfa = stack.pop();
            this.start = nfa.getStart();
            this.end = nfa.getEnd();
        }
    }
}
