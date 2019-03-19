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
        String word1 = "";
        String word2 = "b";
        String word3 = "ab";
        String word4 = "aaaab";
        String word5 = "bb";
        String language2 = "a*b";
        Matcher cm = new Matcher(language2);
        System.out.println("false " + cm.wordMatches(word1));
        System.out.println("true " + cm.wordMatches(word2));
        System.out.println("true " + cm.wordMatches(word3));
        System.out.println("true " + cm.wordMatches(word4));
        System.out.println("false " + cm.wordMatches(word5));
        String l = "(aba)|(abb)";

        Matcher m = new Matcher(l);
        System.out.println(m.wordMatches("aba"));
        System.out.println(m.wordMatches("abb"));
    }
}
