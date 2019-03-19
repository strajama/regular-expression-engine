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
public class Union implements Rule {
    
    private Nfa nfa;

    public Union(Nfa first, Nfa second) {
        State start = new EpsilonState(false);
        start.addTransition(new EpsilonTransition(start, first.getStart()));
        start.addTransition(new EpsilonTransition(start, second.getStart()));
        
        State end = new EpsilonState(true);
        end.addTransition(new EpsilonTransition(first.getEnd(), end));
        first.getEnd().setIsEnd(false);
        end.addTransition(new EpsilonTransition(second.getEnd(), end));
        second.getEnd().setIsEnd(false);
        this.nfa = new EpsilonNfa(start, end);
    }

    public Nfa getNfa() {
        return nfa;
    }
    
    
    
    
}
