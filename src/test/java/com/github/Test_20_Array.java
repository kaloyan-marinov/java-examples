package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_20_Array {
    @Test
    void length() {
        // Arrange.
        int[] integers = new int[] {1, 2, 3, 4, 5};

        // Act.
        int observed = integers.length;

        // Assert.
        int expected = 5;
        assertEquals(expected, observed);
    }

    @Test
    void accessElement() {
        // Arrange.
        String[] names = new String[] {"Alice", "Bob", "Charlie"};

        // Act.
        String observed = names[1];

        // Assert.
        String expected = "Bob";
        assertEquals(expected, observed);
    }

    @Test
    void concatenateArrays() {
        // Arrange.
        int[] ones = new int[] {1, 2};
        int[] tens = new int[] {30, 40, 50};

        // Act.
        int[] expected = new int[ones.length + tens.length];

        for (int i = 0; i < expected.length; i++) {
            if (i < ones.length) {
                expected[i] = ones[i];
            } else {
                expected[i] = tens[i - ones.length];
            }
        }

        // Assert.
        int[] observed = new int[] {1, 2, 30, 40, 50};
        assertArrayEquals(expected, observed);
    }
}
