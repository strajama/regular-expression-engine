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
    
    public static void main(String []args) {
        Postfix p = new Postfix("(a|b)*c");
        System.out.println("printtaa");
        System.out.println(p.getPostfix());
    }
}
