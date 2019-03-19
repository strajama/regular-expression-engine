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
public class NfaBuilder {

    private Nfa nfa;

    public NfaBuilder(String postfix) {
        if ("".equals(postfix)) {
            this.nfa = new EpsilonNfa();
        } else {
            Stack<Nfa> stack = new Stack();

            for (int i = 0; i < postfix.length(); i++) {
                char token = postfix.charAt(i);
                switch (token) {
                    case '*':
                        stack.push(new Closure(stack.pop()).getNfa());
                        break;
                    case '|':
                        {
                            Nfa right = stack.pop();
                            Nfa left = stack.pop();
                            stack.push(new Union(left, right).getNfa());
                            break;
                        }
                    case '·':
                        {
                            Nfa right = stack.pop();
                            Nfa left = stack.pop();
                            stack.push(new Concat(left, right).getNfa());
                            break;
                        }
                    default:
                        stack.push(new SymbolNfa(token));
                        break;
                }
            }
            this.nfa = stack.pop();
        }
    }

    public Nfa getNfa() {
        return nfa;
    }

}
/*
function toNFA(postfixExp) {
    if(postfixExp === '') {
        return fromEpsilon();
    }

    const stack = [];

    for (const token of postfixExp) {
        if(token === '*') {
            stack.push(closure(stack.pop()));
        } else if (token === '|') {
            const right = stack.pop();
            const left = stack.pop();
            stack.push(union(left, right));
        } else if (token === '.') {
            const right = stack.pop();
            const left = stack.pop();
            stack.push(concat(left, right));
        } else {
            stack.push(fromSymbol(token));
        }
    }

    return stack.pop();
}
*/
