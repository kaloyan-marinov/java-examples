package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_14_StringFormatting {
    @Test
    void formatted() {
        // Arrange.
        String messageWithGaps = "John Doe has %d %s.";
        Integer countOfDogs = 2;
        String dogs = countOfDogs == 1 ? "dog" : "dogs";

        // Act.
        String observed = messageWithGaps.formatted(
            countOfDogs,
            dogs
        );

        // Assert.
        String expected = "John Doe has 2 dogs.";
        assertEquals(expected, observed);
    }

    @Test
    void format() {
        // Arrange.
        String messageWithGaps = "Mary Smith has %d %s.";
        Integer countOfCats = 1;
        String cats = countOfCats == 1 ? "cat" : "cats";

        // Act.
        String observed = messageWithGaps.formatted(
            countOfCats,
            cats
        );

        // Assert.
        String expected = "Mary Smith has 1 cat.";
        assertEquals(expected, observed);
    }
}
