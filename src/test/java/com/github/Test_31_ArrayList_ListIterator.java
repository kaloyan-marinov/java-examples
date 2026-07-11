package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

public class Test_31_ArrayList_ListIterator {
    /**
     * as stated on https://www.baeldung.com/java-arraylist#Iterating :
     * 
     * We have two types of iterators available: `Iterator` and `ListIterator`.
     * 
     * We use
     * an `Iterator` to traverse the list in one direction only
     * and
     * a `ListIterator` to traverse it in both directions.
     */
    @Test
    void iterateBackwards() {
        // Arrange.
        List<String> letters = new ArrayList<>(
            Arrays.asList("a", "b", "c")
        );

        int initialIdx = letters.size();

        
        // Act.
        List<String> reversedLetters = new ArrayList<>();
        
        ListIterator<String> listIterator = letters.listIterator(initialIdx);
        while (listIterator.hasPrevious()) {
            String element = listIterator.previous();
            reversedLetters.add(element);
        }

        // Assert.
        String[] observedReversedLetters = reversedLetters.toArray(
            new String[0]
        );
        String[] expectedReversedStrings = {"c", "b", "a"};
        assertArrayEquals(expectedReversedStrings, observedReversedLetters);
    }
}
