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
public class EpsilonTransition implements Transition {

    State from;
    State to;

    public EpsilonTransition(State from, State to) {
        this.from = from;
        this.to = to;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }
}
