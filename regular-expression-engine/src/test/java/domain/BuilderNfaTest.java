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
    }

    @Test
    public void defaultCase() {
        Nfa builder = new BuilderNfa("a");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasSymbolTransition());
        assertFalse(builder.getEnd().hasSymbolTransition());
        assertEquals('a', builder.getStart().getSymbolTransition().getSymbol());
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
    }

    @Test
    public void concatCase() {
        Nfa builder = new BuilderNfa("ab·");
        assertTrue(builder.getEnd().getIsEnd());
        assertFalse(builder.getStart().getIsEnd());
        assertTrue(builder.getStart().hasSymbolTransition());
        assertEquals('a', builder.getStart().getSymbolTransition().getSymbol());
    }
}
