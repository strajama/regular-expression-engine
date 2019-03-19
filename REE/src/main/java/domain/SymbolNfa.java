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
public class SymbolNfa implements Nfa {
    
    private State start;
    private State end;
    private char symbol;

    public SymbolNfa(char symbol) {
        this.start = new State(false);
        this.end = new State(true);
        this.symbol = symbol;
        start.addTransition(new SymbolTransition(start, end, symbol));
    }

    public State getStart() {
        return start;
    }

    public State getEnd() {
        return end;
    }
    
    
}
