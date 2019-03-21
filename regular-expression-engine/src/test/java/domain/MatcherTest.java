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
public class MatcherTest {

    public MatcherTest() {
    }

    @Test
    public void firstMatcher() {
        Matcher matcher = new Matcher("a");
        assertTrue(matcher.wordMatches("a"));
        assertFalse(matcher.wordMatches("aa"));
        assertFalse(matcher.wordMatches("b"));
        assertFalse(matcher.wordMatches(""));
    }

    @Test
    public void secondMatcher() {
        Matcher matcher = new Matcher("a*b");
        assertTrue(matcher.wordMatches("ab"));
        assertTrue(matcher.wordMatches("aaaab"));
        assertTrue(matcher.wordMatches("b"));
        assertFalse(matcher.wordMatches(""));
        assertFalse(matcher.wordMatches("aaa"));
    }

    @Test
    public void thirdMatcher() {
        Matcher matcher = new Matcher("(aba)|(abb)");
        assertTrue(matcher.wordMatches("aba"));
        assertTrue(matcher.wordMatches("abb"));
        assertFalse(matcher.wordMatches("b"));
        assertFalse(matcher.wordMatches(""));
        assertFalse(matcher.wordMatches("a"));
    }
    
        @Test
    public void fourthMatcher() {
        Matcher matcher = new Matcher("(aa)*");
        assertTrue(matcher.wordMatches(""));
        assertTrue(matcher.wordMatches("aa"));
        assertTrue(matcher.wordMatches("aaaa"));
        assertFalse(matcher.wordMatches("a"));
        assertFalse(matcher.wordMatches("aaa"));
        assertFalse(matcher.wordMatches("b"));
    }
}