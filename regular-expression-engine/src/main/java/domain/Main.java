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
        String language = "(ab)*|a*";
        Postfix p = new Postfix(language);
        System.out.println("printtaa " + language);
        System.out.println(p.toString());


        
    }
}
