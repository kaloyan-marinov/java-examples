package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// TODO: (2026/07/14, 21:07)
//      find out why the `Exclamation` class does not need to be imported explicitly

public class Test_01_Exclamation {
    @Test
    void build() {
        // Arrange.
        String word = "attention";

        // Act.
        String observed = Exclamation.build(word);

        // Assert.
        String expected = "ATTENTION!";
        assertEquals(expected, observed);
    }

    @Test
    void buildReceivesNullAsInput() {
        // Act + Assert.
        Exception exception = assertThrows(
            NullPointerException.class,
            () -> {
                Exclamation.build(null);
            }
        );

        String observed = exception.getMessage();

        String expected = "Cannot invoke \"String.toUpperCase()\" because \"word\" is null";
        assertEquals(expected, observed);
    }
}
