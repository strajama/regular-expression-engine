/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;

import domain.Nfa;

/**
 * Stack data structure for Nfas
 *
 * @author strajama
 */
public class NfaStack implements Stack<Nfa> {

    private int top;
    private Nfa[] nfas;

    /**
     * Creates new NfaStack for 10 Nfas
     */
    public NfaStack() {
        this.top = -1;
        this.nfas = new Nfa[10];
    }

    /**
     * Pushes new Nfa to the stack. If stack's array is full it will create new
     * bigger array for the stack
     *
     * @param t - Nfa
     */
    @Override
    public void push(Nfa t) {
        this.top++;
        if (this.top > this.nfas.length - 1) {
            biggerArray();
        }
        this.nfas[this.top] = t;
    }

    /**
     * Pops the most recently pushed Nfa from the stack
     *
     * @return Nfa or null if stack is empty
     */
    @Override
    public Nfa pop() {
        if (this.empty()) {
            return null;
        }
        this.top--;
        return this.nfas[this.top + 1];
    }

    /**
     * Peek which is the most recently pushed Nfa in the stack
     *
     * @return Nfa or null if stack is empty
     */
    @Override
    public Nfa peek() {
        if (this.empty()) {
            return null;
        }
        return nfas[top];
    }

    /**
     * Check if stack is empty
     *
     * @return true if stack is empty, otherwise false;
     */
    @Override
    public boolean empty() {
        return this.top < 0;
    }

    /**
     * Creates bigger array for the stack
     */
    private void biggerArray() {
        Nfa[] big = new Nfa[this.nfas.length * 2];
        for (int i = 0; i < this.nfas.length; i++) {
            big[i] = this.nfas[i];
        }
        this.nfas = big;
    }

}
