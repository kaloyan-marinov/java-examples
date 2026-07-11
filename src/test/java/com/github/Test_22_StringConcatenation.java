package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_22_StringConcatenation {
    @Test
    void concatenate() {
        // Arrange.
        String[] words = {"this", "is", "a", "sequence", "of", "words"};
        String delimiter = " ";

        // Act.
        String observedSentence = String.join(delimiter, words);

        // Assert.
        String expectedSentence = "this is a sequence of words";

        assertEquals(expectedSentence, observedSentence);
    }
}
