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
public class ClosureNfa implements Nfa {

    private State start;
    private State end;

    public ClosureNfa(Nfa nfa) {
        this.start = new State(false);
        this.end = new State(true);
        this.start.addTransition(new EpsilonTransition(this.start, this.end));
        this.start.addTransition(new EpsilonTransition(this.start, nfa.getStart()));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), this.end));
        nfa.getEnd().addTransition(new EpsilonTransition(nfa.getEnd(), nfa.getStart()));
        nfa.getEnd().setIsEnd(false);
    }

    public State getStart() {
        return this.start;
    }

    public State getEnd() {
        return this.end;
    }

    

}
