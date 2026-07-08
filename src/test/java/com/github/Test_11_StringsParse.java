package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Test_11_StringsParse {
    @Test
    void parseToInt() {
        // Arrange.
        String theNumber17 = "17";

        // Act.
        int observed = Integer.parseInt(theNumber17);

        // Assert.
        int expected = 17;
        assertEquals(expected, observed);
    }

    @Test
    void parseToInteger() {
        // Arrange.
        String theNumber27 = "27";

        // Act.
        Integer observed = Integer.valueOf(theNumber27);

        // Assert.
        int expected = 27;

        assertTrue(expected == observed);
        assertEquals(expected, observed);
    }
}
