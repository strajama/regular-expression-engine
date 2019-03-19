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
public class SymbolTransition implements Transition {
    
    State from;
    State to;
    char symbol;

    public SymbolTransition(State from, State to, char symbol) {
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    public char getSymbol() {
        return symbol;
    }
    
    public boolean hasSymbol() {
        return true;
    }
}
