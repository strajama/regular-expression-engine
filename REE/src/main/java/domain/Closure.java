/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author strajama
 */
public class Closure implements Rule {
    
    private Nfa nfa;

    public Closure(Nfa nfa) {
        State start = new EpsilonState(false);
        State end = new EpsilonState(true);
        start.addTransition(new EpsilonTransition(start, end));
        start.addTransition(new EpsilonTransition(start, nfa.getStart()));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), end));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), nfa.getStart()));
        nfa.getEnd().setIsEnd(false);
        this.nfa = new EpsilonNfa(start, end);
    }

    public Nfa getNfa() {
        return nfa;
    }
    
    
    
}
