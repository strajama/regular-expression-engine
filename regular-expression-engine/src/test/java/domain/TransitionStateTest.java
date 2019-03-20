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
public class TransitionStateTest {

    State startS;
    State endS;
    State startE;
    State endE;
    SymbolTransition st;
    EpsilonTransition et;

    public TransitionStateTest() {
    }

    @Before
    public void setUp() {
        startS = new State(false);
        endS = new State(true);
        startE = new State(false);
        endE = new State(true);
        st = new SymbolTransition(startS, endS, 'a');
        et = new EpsilonTransition(startE, endE);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void statesCreatedAndHaveEnd() {
        assertFalse(startS.getIsEnd());
        assertTrue(endS.getIsEnd());
        assertEquals(0, startS.numberOfEpsilons());
        assertEquals(0, endS.numberOfEpsilons());
    }

    @Test
    public void symbolTransition() {
        assertTrue(st.hasSymbol());
        assertEquals('a', st.getSymbol());
        assertFalse(startS.hasSymbolTransition());
        assertFalse(startS.hasEpsilonTransitions());
        startS.addTransition(st);
        assertTrue(startS.hasSymbolTransition());
        assertFalse(startS.hasEpsilonTransitions());
        assertEquals(st, startS.getSymbolTransition());
        assertEquals(startS, st.getFrom());
        assertEquals(endS, st.getTo());
    }

    @Test
    public void epsilonTransition() {
        assertFalse(et.hasSymbol());
        assertFalse(startE.hasEpsilonTransitions());
        assertFalse(startE.hasSymbolTransition());
        startE.addTransition(et);
        assertTrue(startE.hasEpsilonTransitions());
        assertEquals(1, startE.numberOfEpsilons());
        startE.addTransition(st);
        assertFalse(startE.hasSymbolTransition());
        assertTrue(startE.getEpsilonTransitions()[0] == et);
        assertEquals(startE, et.getFrom());
        assertEquals(endE, et.getTo());
        //EpsilonTransition et2 = new EpsilonTransition(startS, endS);
        startE.addTransition(et);
        assertEquals(2, startE.numberOfEpsilons());
        startE.addTransition(et);
        assertEquals(2, startE.numberOfEpsilons());
    }
}
