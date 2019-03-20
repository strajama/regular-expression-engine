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
    public void insertConcatPostfix() {
        Postfix postfix = new Postfix("ab");
        assertEquals("ab·", postfix.toString());
    }

    @Test
    public void toPostFix() {
        Postfix postfix1 = new Postfix("(a|b)*c");
        assertEquals("ab|*c·", postfix1.toString());
        Postfix postfix2 = new Postfix("(aba)|(abb)");
        assertEquals("ab·a·ab·b·|", postfix2.toString());
        Postfix postfix3 = new Postfix("(aba)*|(a(ba)b)");
        assertEquals("ab·a·*aba··b·|", postfix3.toString());
        Postfix postfix4 = new Postfix("(ab*(a))*|(a(ba)b)");
        assertEquals("ab*·a·*aba··b·|", postfix4.toString());
        Postfix postfix5 = new Postfix("(a*b*|c*)*c");
        assertEquals("a*b*·c*|*c·", postfix5.toString());
    }
}
