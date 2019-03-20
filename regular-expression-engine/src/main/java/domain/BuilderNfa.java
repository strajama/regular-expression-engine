/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Stack;

/**
 *
 * @author strajama
 */
public class BuilderNfa implements Nfa {

    private State start;
    private State end;

    public BuilderNfa(String postfix) {
        Nfa nfa = new EpsilonNfa();
        if ("".equals(postfix)) {
            this.start = nfa.getStart();
            this.end = nfa.getEnd();
        } else {
            Stack<Nfa> stack = new Stack();

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

    public State getStart() {
        return this.start;
    }

    public State getEnd() {
        return this.end;
    }
}
