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
public class ConcatNfa implements Nfa {

    private State start;
    private State end;

    public ConcatNfa(Nfa first, Nfa second) {
        first.getEnd().addTransition(new EpsilonTransition(first.getEnd(), second.getStart()));
        first.getEnd().setIsEnd(false);
        this.start = first.getStart();
        this.end = second.getEnd();
    }

    public State getStart() {
        return this.start;
    }

    public State getEnd() {
        return this.end;
    }

}
