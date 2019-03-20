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
public class NfaTest {

    SymbolNfa sNfa;
    EpsilonNfa eNfa;

    public NfaTest() {
    }

    @Before
    public void setUp() {
        sNfa = new SymbolNfa('a');
        eNfa = new EpsilonNfa();
    }

    @Test
    public void symbolNfa() {
        assertFalse(sNfa.getStart().getIsEnd());
        assertTrue(sNfa.getEnd().getIsEnd());
        assertTrue(sNfa.getStart().hasSymbolTransition());
        assertEquals(sNfa.getStart().getSymbolTransition().getSymbol(), 'a');
        assertEquals(sNfa.getStart().getSymbolTransition().getTo(), sNfa.getEnd());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        assertFalse(sNfa.getEnd().hasEpsilonTransitions());
        assertFalse(sNfa.getEnd().hasSymbolTransition());
    }

    @Test
    public void epsilonNfa() {
        assertFalse(eNfa.getStart().getIsEnd());
        assertTrue(eNfa.getEnd().getIsEnd());
        assertTrue(eNfa.getStart().hasEpsilonTransitions());
        EpsilonTransition et = eNfa.getStart().getEpsilonTransitions().get(0);
        assertEquals(et.getTo(), eNfa.getEnd());
        assertFalse(eNfa.getStart().hasSymbolTransition());
        assertFalse(eNfa.getEnd().hasEpsilonTransitions());
        assertFalse(eNfa.getEnd().hasSymbolTransition());
    }

    @Test
    public void unionNfa() {
        assertTrue(sNfa.getEnd().getIsEnd());
        assertTrue(eNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getEnd().hasEpsilonTransitions());
        assertFalse(eNfa.getEnd().hasEpsilonTransitions());
        Nfa union = new UnionNfa(sNfa, eNfa);
        State firstS = union.getStart().getEpsilonTransitions().get(0).getTo();
        State secondS = union.getStart().getEpsilonTransitions().get(1).getTo();
        assertEquals(sNfa.getStart(), firstS);
        assertEquals(eNfa.getStart(), secondS);
        assertFalse(sNfa.getEnd().getIsEnd());
        assertFalse(eNfa.getEnd().getIsEnd());
        assertEquals(sNfa.getEnd().getEpsilonTransitions().get(0).getTo(), union.getEnd());
        assertEquals(eNfa.getEnd().getEpsilonTransitions().get(0).getTo(), union.getEnd());
    }

    @Test
    public void closureNfa() {
        assertTrue(sNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getEnd().hasEpsilonTransitions());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        Nfa closure = new ClosureNfa(sNfa);
        assertFalse(sNfa.getEnd().getIsEnd());
        assertTrue(sNfa.getEnd().hasEpsilonTransitions());
        assertEquals(2, sNfa.getEnd().getEpsilonTransitions().size());
        assertEquals(sNfa.getEnd().getEpsilonTransitions().get(0).getTo(), closure.getEnd());
        assertEquals(sNfa.getEnd().getEpsilonTransitions().get(1).getTo(), sNfa.getStart());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        assertTrue(closure.getStart().hasEpsilonTransitions());
        assertFalse(closure.getEnd().hasEpsilonTransitions());
        assertTrue(closure.getEnd().getIsEnd());
        assertEquals(2, closure.getStart().getEpsilonTransitions().size());
        assertEquals(closure.getStart().getEpsilonTransitions().get(0).getTo(), closure.getEnd());
        assertEquals(closure.getStart().getEpsilonTransitions().get(1).getTo(), sNfa.getStart());
    }

    @Test
    public void concatNfa() {
        assertTrue(eNfa.getEnd().getIsEnd());
        assertFalse(sNfa.getStart().hasEpsilonTransitions());
        assertFalse(eNfa.getEnd().hasEpsilonTransitions());
        Nfa concat = new ConcatNfa(eNfa, sNfa);
        assertFalse(eNfa.getEnd().getIsEnd());
        assertTrue(eNfa.getEnd().hasEpsilonTransitions());
        assertEquals(1, eNfa.getEnd().getEpsilonTransitions().size());
        assertEquals(eNfa.getEnd().getEpsilonTransitions().get(0).getTo(), sNfa.getStart());
        assertEquals(eNfa.getStart(), concat.getStart());
        assertEquals(sNfa.getEnd(), concat.getEnd());
    }

}
