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
public class EpsilonNfa implements Nfa {
    
    private State start;
    private State end;

    public EpsilonNfa() {
        this.start = new EpsilonState(false);
        this.end = new EpsilonState(true);
        start.addTransition(new EpsilonTransition(start, end));
    }

    public EpsilonNfa(State start, State end) {
        this.start = start;
        this.end = end;
    }

    public State getStart() {
        return start;
    }

    public State getEnd() {
        return end;
    }

}
