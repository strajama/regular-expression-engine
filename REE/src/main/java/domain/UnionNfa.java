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
public class UnionNfa implements Nfa {
    
    private State start;
    private State end;

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

    public State getStart() {
        return this.start;
    }

    public State getEnd() {
        return this.end;
    }
    
}
