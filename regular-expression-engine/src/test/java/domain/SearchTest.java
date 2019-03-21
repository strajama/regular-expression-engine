/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class SearchTest {

    private EpsilonNfa nfa;

    public SearchTest() {
    }

    @Before
    public void setUp() {
        nfa = new EpsilonNfa();
    }

    @Test
    public void emptySearch() {
        EpsilonNfa epsilon = new EpsilonNfa();
        Search search = new Search(epsilon, "");
        assertTrue(search.findsEnd());
        Search search2 = new Search(epsilon, "a");
        assertFalse(search2.findsEnd());
    }

    @Test
    public void singleCharacter() {
        SymbolNfa symbol = new SymbolNfa('a');
        Search search = new Search(symbol, "a");
        assertTrue(search.findsEnd());
        Search search2 = new Search(symbol, "b");
        assertFalse(search2.findsEnd());
    }

    @Test
    public void withBuilderNfaAsterisk() {
        Postfix postfix1 = new Postfix("aa*");
        BuilderNfa nfa = new BuilderNfa(postfix1.toString());
        Search search1 = new Search(nfa, "a");
        assertTrue(search1.findsEnd());
        Search search2 = new Search(nfa, "aa");
        assertTrue(search2.findsEnd());
        Search search3 = new Search(nfa, "aaa");
        assertTrue(search3.findsEnd());
        Search search4 = new Search(nfa, "");
        assertFalse(search4.findsEnd());
        Search search5 = new Search(nfa, "b");
        assertFalse(search5.findsEnd());
    }

    @Test
    public void withBuilderNfaVerticalbar() {
        Postfix postfix1 = new Postfix("a|b");
        BuilderNfa nfa = new BuilderNfa(postfix1.toString());
        Search search1 = new Search(nfa, "a");
        assertTrue(search1.findsEnd());
        Search search2 = new Search(nfa, "b");
        assertTrue(search2.findsEnd());
        Search search4 = new Search(nfa, "");
        assertFalse(search4.findsEnd());
        Search search5 = new Search(nfa, "ab");
        assertFalse(search5.findsEnd());
    }

    @Test
    public void combinations1() {
        Postfix postfix1 = new Postfix("(a|b)*");
        BuilderNfa nfa = new BuilderNfa(postfix1.toString());
        Search search1 = new Search(nfa, "a");
        assertTrue(search1.findsEnd());
        Search search2 = new Search(nfa, "aa");
        assertTrue(search2.findsEnd());
        Search search3 = new Search(nfa, "aab");
        assertTrue(search3.findsEnd());
        Search search4 = new Search(nfa, "c");
        assertFalse(search4.findsEnd());
        Search search5 = new Search(nfa, "bc");
        assertFalse(search5.findsEnd());
        Search search6 = new Search(nfa, "ac");
        assertFalse(search6.findsEnd());
        Search search7 = new Search(nfa, "ab");
        assertTrue(search7.findsEnd());
        Search search8 = new Search(nfa, "aba");
        assertTrue(search8.findsEnd());
        Search search9 = new Search(nfa, "bbb");
        assertTrue(search9.findsEnd());
    }
    
        public void combinations2() {
        Postfix postfix1 = new Postfix("(a|b)*|c*(ab)");
        BuilderNfa nfa = new BuilderNfa(postfix1.toString());
        Search search1 = new Search(nfa, "");
        assertTrue(search1.findsEnd());
        Search search2 = new Search(nfa, "ab");
        assertTrue(search2.findsEnd());
        Search search3 = new Search(nfa, "cccab");
        assertTrue(search3.findsEnd());
        Search search4 = new Search(nfa, "d");
        assertFalse(search4.findsEnd());
        Search search5 = new Search(nfa, "c");
        assertFalse(search5.findsEnd());
        Search search6 = new Search(nfa, "abc");
        assertFalse(search6.findsEnd());
        Search search7 = new Search(nfa, "abab");
        assertTrue(search7.findsEnd());
        Search search8 = new Search(nfa, "aaaaabab");
        assertTrue(search8.findsEnd());
        Search search9 = new Search(nfa, "bbb");
        assertTrue(search9.findsEnd());
    }
}
