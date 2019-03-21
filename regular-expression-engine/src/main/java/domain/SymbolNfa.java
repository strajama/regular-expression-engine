/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * SymbolNfa is basic building block for more complex Nfa's with symbol
 * transition.
 *
 * @author strajama
 */
public class SymbolNfa extends NfaClass {

    /**
     * Creates new SymbolNfa with beginning and end state and symbol transition
     * between them.
     *
     * @param symbol - character that is used to create symbol transition
     */
    public SymbolNfa(char symbol) {
        this.start = new State(false);
        this.end = new State(true);
        start.addTransition(new SymbolTransition(start, end, symbol));
    }
}
