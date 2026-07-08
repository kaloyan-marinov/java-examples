package com.github;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestStringsCompare {
    @Test
    void compareStringLiterals() {
        // Arrange.
        String s = "hello";
        String t = "hello";

        // Act.
        boolean areTheyRefentiallyEqual = (s == t);

        boolean storeSameCharactersInSameOrder = s.equals(t);

        // Assert.
        assertTrue(areTheyRefentiallyEqual);

        assertTrue(storeSameCharactersInSameOrder);
    }

    @Test
    void compareStringLiteralAndString() {
        // Arrange.
        String u = "hello";
        String v = new String("hello");

        // Act.
        boolean areTheyRefentiallyEqual = (u == v);

        boolean storeSameCharactersInSameOrder = u.equals(v);

        // Assert.
        assertFalse(areTheyRefentiallyEqual);

        assertTrue(storeSameCharactersInSameOrder);
    }
}
