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
public class Matcher {
    
    private String postfixExp;
    private Nfa nfa;

    public Matcher(String expression) {
        this.postfixExp = new Postfix(expression).getPostfixString();
        this.nfa = new NfaBuilder(postfixExp).getNfa();        
    }
    
    public boolean wordMatches(String word) {
        Search search = new Search (this.nfa, word);
        return search.findsEnd();
    }
    
    
}
