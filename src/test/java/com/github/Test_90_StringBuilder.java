package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Test_90_StringBuilder {
    @Test
    void _toString() {
        // Arrange.
        StringBuilder sb = new StringBuilder();

        // Act.
        sb
            .append("hello")
            .append("\n")
            .append("world")
            .append("\n")
        ;

        String observedString = sb.toString();

        // Assert.
        String expectedString = """
            hello
            world
            """;

        boolean areEqual = expectedString.equals(observedString);
        assertTrue(areEqual);
    }

    @Test
    void charAt() {
        // Arrange.
        StringBuilder sb = new StringBuilder();

        // Act.
        sb
            .append("Pizza")
            .append(" ")
            .append("Margherita")
        ;

        char observed = sb.charAt(6);

        // Assert.
        char expected = 'M';
        assertEquals(expected, observed);
    }
}
