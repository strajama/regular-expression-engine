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
public class NfaTest {

//    State startS;
//    State endS;
//    State startE;
//    State endE;
//    SymbolTransition st;
//    EpsilonTransition et;
    SymbolNfa sNfa;
    EpsilonNfa eNfa;

    public NfaTest() {
    }

    @Before
    public void setUp() {
//        startS = new State(false);
//        endS = new State(true);
//        startE = new State(false);
//        endE = new State(true);
//        st = new SymbolTransition(startS, endS, 'a');
//        et = new EpsilonTransition(startE, endE);
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
        State endS = sNfa.getEnd();
        State endE = eNfa.getEnd();
        assertTrue(endS.getIsEnd());
        assertTrue(endE.getIsEnd());
        assertFalse(endS.hasEpsilonTransitions());
        assertFalse(endE.hasEpsilonTransitions());
        Nfa union = new UnionNfa(sNfa, eNfa);
        State firstS = union.getStart().getEpsilonTransitions().get(0).getTo();
        State secondS = union.getStart().getEpsilonTransitions().get(1).getTo();
        assertEquals(sNfa.getStart(), firstS);
        assertEquals(eNfa.getStart(), secondS);
        assertFalse(endS.getIsEnd());
        assertFalse(endE.getIsEnd());
        assertEquals(endS.getEpsilonTransitions().get(0).getTo(), union.getEnd());
        assertEquals(endE.getEpsilonTransitions().get(0).getTo(), union.getEnd());
    }

}
