/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class StackTest {

    public StackTest() {
    }

    @Test
    public void charStack() {
        CharStack stack = new CharStack();
        assertTrue(stack.empty());
        assertEquals(null, stack.peek());
        assertEquals(null, stack.pop());
        char a = 'a';
        stack.push(a);
        assertFalse(stack.empty());
        assertTrue(a == stack.peek());
        assertTrue(a == stack.pop());
        assertTrue(stack.empty());
        for (int i = 1; i < 12; i++) {
            stack.push(a);
            assertFalse(stack.empty());
        }
        char b = 'b';
        stack.push(b);
        assertTrue(b == stack.pop());
        for (int j = 1; j < 11; j++) {
            assertTrue(a == stack.pop());
            assertFalse(stack.empty());
        }
        assertTrue(a == stack.pop());
        assertTrue(stack.empty());
    }
}
