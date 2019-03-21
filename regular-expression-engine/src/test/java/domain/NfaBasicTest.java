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
public class NfaBasicTest {

    SymbolNfa sNfa;
    EpsilonNfa eNfa;

    public NfaBasicTest() {
    }

    @Before
    public void setUp() {
        sNfa = new SymbolNfa('a');
        eNfa = new EpsilonNfa();
    }

    @Test
    public void symbolNfa() {
        SymbolNfa symbol = new SymbolNfa('b');
        assertFalse(symbol.getStart().getIsEnd());
        assertTrue(symbol.getEnd().getIsEnd());
        assertTrue(symbol.getStart().hasSymbolTransition());
        assertEquals(symbol.getStart().getSymbolTransition().getSymbol(), 'b');
        assertEquals(symbol.getStart().getSymbolTransition().getTo(), symbol.getEnd());
        assertFalse(symbol.getStart().hasEpsilonTransitions());
        assertFalse(symbol.getEnd().hasEpsilonTransitions());
        assertFalse(symbol.getEnd().hasSymbolTransition());
    }

    @Test
    public void epsilonNfa() {
        EpsilonNfa epsilon = new EpsilonNfa();
        assertFalse(epsilon.getStart().getIsEnd());
        assertTrue(epsilon.getEnd().getIsEnd());
        assertTrue(epsilon.getStart().hasEpsilonTransitions());
        assertEquals(1, epsilon.getStart().numberOfEpsilons());
        EpsilonTransition et = epsilon.getStart().getEpsilonTransitions()[0];
        assertEquals(et.getTo(), epsilon.getEnd());
        assertFalse(epsilon.getStart().hasSymbolTransition());
        assertFalse(epsilon.getEnd().hasEpsilonTransitions());
        assertFalse(epsilon.getEnd().hasSymbolTransition());
    }

    @Test
    public void unionNfa() {
        assertTrue(sNfa.getEnd().getIsEnd());
        assertTrue(eNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getEnd().hasEpsilonTransitions());
        assertFalse(eNfa.getEnd().hasEpsilonTransitions());
        Nfa union = new UnionNfa(sNfa, eNfa);
        State firstS = union.getStart().getEpsilonTransitions()[0].getTo();
        State secondS = union.getStart().getEpsilonTransitions()[1].getTo();
        assertEquals(sNfa.getStart(), firstS);
        assertEquals(eNfa.getStart(), secondS);
        assertFalse(sNfa.getEnd().getIsEnd());
        assertFalse(eNfa.getEnd().getIsEnd());
        assertEquals(sNfa.getEnd().getEpsilonTransitions()[0].getTo(), union.getEnd());
        assertEquals(eNfa.getEnd().getEpsilonTransitions()[0].getTo(), union.getEnd());
    }

    @Test
    public void closureNfa() {
        assertTrue(sNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getEnd().hasEpsilonTransitions());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        Nfa closure = new ClosureNfa(sNfa);
        assertFalse(sNfa.getEnd().getIsEnd());
        assertTrue(sNfa.getEnd().hasEpsilonTransitions());
        assertEquals(2, sNfa.getEnd().numberOfEpsilons());
        assertEquals(sNfa.getEnd().getEpsilonTransitions()[0].getTo(), closure.getEnd());
        assertEquals(sNfa.getEnd().getEpsilonTransitions()[1].getTo(), sNfa.getStart());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        assertTrue(closure.getStart().hasEpsilonTransitions());
        assertFalse(closure.getEnd().hasEpsilonTransitions());
        assertTrue(closure.getEnd().getIsEnd());
        assertEquals(2, closure.getStart().numberOfEpsilons());
        assertEquals(closure.getStart().getEpsilonTransitions()[0].getTo(), closure.getEnd());
        assertEquals(closure.getStart().getEpsilonTransitions()[1].getTo(), sNfa.getStart());
    }

    @Test
    public void concatNfa() {
        assertTrue(eNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        assertFalse(eNfa.getEnd().hasEpsilonTransitions());
        Nfa concat = new ConcatNfa(eNfa, sNfa);
        assertFalse(eNfa.getEnd().getIsEnd());
        assertTrue(eNfa.getEnd().hasEpsilonTransitions());
        assertEquals(1, eNfa.getEnd().numberOfEpsilons());
        assertEquals(eNfa.getEnd().getEpsilonTransitions()[0].getTo(), sNfa.getStart());
        assertEquals(eNfa.getStart(), concat.getStart());
        assertEquals(sNfa.getEnd(), concat.getEnd());
    }
}
