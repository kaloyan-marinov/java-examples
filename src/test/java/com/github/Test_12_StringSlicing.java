package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_12_StringSlicing {
    @Test
    void substringStartIdx() {
        // Arrange.
        String s = "__Hello!";

        // Act.
        String observed = s.substring(2);

        // Assert.
        String expected = "Hello!";
        assertEquals(expected, observed);
    }

    @Test
    void substringStartIdxAndEndIdx() {
        // Arrange.
        String s = "__abc__";

        // Act.
        String observed = s.substring(2, 5);

        // Assert.
        String expected = "abc";
        assertEquals(expected, observed);
    }
}
