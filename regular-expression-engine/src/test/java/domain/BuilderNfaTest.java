/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class BuilderNfaTest {

    public BuilderNfaTest() {
    }

    @Test
    public void emptyPostfix() {
        Nfa builder = new BuilderNfa("");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasEpsilonTransitions());
        assertFalse(builder.getEnd().hasEpsilonTransitions());
        assertEquals(1, builder.getStart().numberOfEpsilons());
        assertEquals(builder.getEnd(), builder.getStart().getEpsilonTransitions()[0].getTo());
        assertTrue(builder.search(""));
        assertFalse(builder.search("a"));
    }

    @Test
    public void defaultCase() {
        Nfa builder = new BuilderNfa("a");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasSymbolTransition());
        assertFalse(builder.getEnd().hasSymbolTransition());
        assertEquals('a', builder.getStart().getSymbolTransition().getSymbol());
        assertTrue(builder.search("a"));
        assertFalse(builder.search("b"));
        assertFalse(builder.search("aa"));
    }

    @Test
    public void closureCase() {
        Nfa builder = new BuilderNfa("a*");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasEpsilonTransitions());
        assertEquals(2, builder.getStart().numberOfEpsilons());
        assertEquals(builder.getEnd(), builder.getStart().getEpsilonTransitions()[0].getTo());
        assertEquals('a', builder.getStart().getEpsilonTransitions()[1].getTo().getSymbolTransition().getSymbol());
        assertFalse(builder.search("b"));
        assertTrue(builder.search(""));
        assertTrue(builder.search("a"));
        assertTrue(builder.search("aa"));
        assertTrue(builder.search("aaaaaa"));
    }

    @Test
    public void unionCase() {
        Nfa builder = new BuilderNfa("ab|");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasEpsilonTransitions());
        assertEquals(2, builder.getStart().numberOfEpsilons());
        assertEquals('a', builder.getStart().getEpsilonTransitions()[0].getTo().getSymbolTransition().getSymbol());
        assertEquals('b', builder.getStart().getEpsilonTransitions()[1].getTo().getSymbolTransition().getSymbol());
        assertTrue(builder.search("b"));
        assertFalse(builder.search(""));
        assertTrue(builder.search("a"));
        assertFalse(builder.search("aa"));
        assertFalse(builder.search("ab"));
    }

    @Test
    public void concatCase() {
        Nfa builder = new BuilderNfa("ab·");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasSymbolTransition());
        assertEquals('a', builder.getStart().getSymbolTransition().getSymbol());
        assertFalse(builder.search("b"));
        assertFalse(builder.search(""));
        assertFalse(builder.search("a"));
        assertTrue(builder.search("ab"));
    }
}
