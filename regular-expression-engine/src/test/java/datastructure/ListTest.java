/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;

import domain.State;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class ListTest {

    List list;

    public ListTest() {
    }

    @Before
    public void setUp() {
        list = new List();
    }

    @Test
    public void createList() {
        assertEquals(0, list.getStates().length);
        assertFalse(list.contains(new State(false)));
    }

    @Test
    public void addToList() {
        assertEquals(0, list.getStates().length);
        list.add(new State(true));
        assertEquals(1, list.getStates().length);
        list.add(new State(false));
        assertEquals(2, list.getStates().length);
    }

    @Test
    public void listContainsState() {
        State state = new State(true);
        assertFalse(list.contains(state));
        list.add(state);
        assertTrue(list.contains(state));
    }
}
