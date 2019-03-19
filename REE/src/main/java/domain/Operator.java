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
public enum Operator {
    VERTICALBAR('|', 0),
    INTERPUNCT('·', 1),
    ASTERISK('*', 2);

    private char symbol;
    private int precedence;

    Operator(char symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPrecedence() {
        return precedence;
    }
    
    
}
