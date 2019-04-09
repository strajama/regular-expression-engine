/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class PostfixTest {

    public PostfixTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void postfixIsCreated() {
        Postfix postfix = new Postfix("");
        assertEquals("", postfix.toString());
    }

    @Test
    public void replacePlus() {
        Postfix p1 = new Postfix("a+");
        assertEquals("a*a·", p1.toString());
        Postfix p2 = new Postfix("ab+");
        assertEquals("ab*·b·", p2.toString());
        Postfix p3 = new Postfix("(ab)+");
        assertEquals("ab·*ab··", p3.toString());
    }

    @Test
    public void insertConcatPostfix() {
        Postfix postfix = new Postfix("ab");
        assertEquals("ab·", postfix.toString());
    }

    @Test
    public void toPostFix1() {
        Postfix postfix1 = new Postfix("(a|b)*c");
        assertEquals("ab|*c·", postfix1.toString());
        Postfix postfix2 = new Postfix("(aba)|(abb)");
        assertEquals("ab·a·ab·b·|", postfix2.toString());
        Postfix postfix3 = new Postfix("(aba)*|(a(ba)b)");
        assertEquals("ab·a·*aba··b·|", postfix3.toString());
        Postfix postfix4 = new Postfix("(ab*(a))*|(a(ba)b)");
        assertEquals("ab*·a·*aba··b·|", postfix4.toString());
        Postfix postfix5 = new Postfix("(a(ab)*)");
        assertEquals("aab·*·", postfix5.toString());
    }

    @Test
    public void toPostFix2() {
        Postfix postfix1 = new Postfix("((ab|c)*(ab)*|c*(ab|c))+");
        assertEquals("ab·c|*ab·*·c*ab·c|·|*ab·c|*ab·*·c*ab·c|·|·", postfix1.toString());
        Postfix postfix2 = new Postfix("((ab|c)+(ab)*|c*(ab|c))+");
        assertEquals("ab·c|*ab·c|ab·*·c*ab·c|·|*·ab·*·c*ab·c|·|", postfix2.toString());
        Postfix postfix3 = new Postfix("((ab|c)+(ab)*|c*)(ab|c)+");
        assertEquals("ab·c|*ab·c|ab·*·c*|·ab·c|*·ab·*·c*|ab·c|·", postfix3.toString());
        Postfix postfix4 = new Postfix("((ab|c)*(ab)*|c*)(ab|c)+");
        assertEquals("ab·c|*ab·*·c*|ab·c|*·ab·c|*ab·*·c*|·ab·c|·", postfix4.toString());
        Postfix postfix5 = new Postfix("(a+|b+|c+)+(ab|c)+");
        assertEquals("a*ab*b·|c*c·|*·ab·c|*·ab·c|·", postfix5.toString());
    }
}
