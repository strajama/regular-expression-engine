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
public class Main {

    public static void main(String[] args) {
        String language = "(a|b)*c";
        Postfix p = new Postfix(language);
        System.out.println("printtaa " + language);
        System.out.println(p.getPostfixString());

        String language2 = "a*b";
        Matcher cm = new Matcher(language2);
        System.out.println("false " + cm.wordMatches(""));
        System.out.println("true " + cm.wordMatches("b"));
        System.out.println("true " + cm.wordMatches("ab"));
        System.out.println("true " + cm.wordMatches("aaaaaab"));
        System.out.println("false " + cm.wordMatches("bb"));
        
        String l = "(aba)|(abb)";
        String test = "(aa)*";
        Postfix p1 = new Postfix(test);
        
        Matcher ma = new Matcher(test);
        System.out.println("true "+ ma.wordMatches(""));
        System.out.println("true "+ ma.wordMatches("aa"));
        System.out.println("true "+ ma.wordMatches("aaaa"));
        System.out.println("false "+ ma.wordMatches("a"));
        Matcher m = new Matcher(l);
        System.out.println("false "+m.wordMatches(""));
        System.out.println("true "+m.wordMatches("aba"));
        System.out.println("true "+m.wordMatches("abb"));
    }
}
