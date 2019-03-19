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
public class Concat implements Rule {
    
    private Nfa nfa;

    public Concat(Nfa first, Nfa second) {
        State start = first.getEnd();
        State end = second.getStart();
        start.addTransition(new EpsilonTransition(start, end));
        start.setIsEnd(false);
        this.nfa = new EpsilonNfa(first.getStart(), second.getEnd());
    }

    public Nfa getNfa() {
        return nfa;
    }
    
    
    
}
