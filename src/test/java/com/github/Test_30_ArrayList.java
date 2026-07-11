package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_30_ArrayList {
    @Test
    void initializeAndPopulate() {
        // Arrange.
        List<String> names = new ArrayList<>();

        // Act.
        names.add("Alice");
        names.add("Bob");
        names.add("Byron");
        names.add("Charlie");

        int idxToRemove = 2;
        names.remove(idxToRemove);

        int observedNumOfNames = names.size();

        boolean containsBob = names.contains("Bob");
        boolean containsByron = names.contains("Byron");

        String observedAtIdx2 = names.get(2);

        // Assert.
        int expectedNumOfNames = 3;
        assertEquals(expectedNumOfNames, observedNumOfNames);

        assertTrue(containsBob);
        assertFalse(containsByron);

        String expectedAtIdx2 = "Charlie";
        assertEquals(expectedAtIdx2, observedAtIdx2);
    }

    @Test
    void convertToArray() {
        // Arrange.
        List<String> languages = new ArrayList<>();

        languages.add("English");
        languages.add("Italian");
        languages.add("Finnish");

        // Act.
        String[] observed = languages.toArray(
            new String[0]  // = a zero-length `String` array, essentially saying "Give me back a `String[]`, not an `Object[]`"
        );

        // Assert.
        String[] expected = {
            "English",
            "Italian",
            "Finnish"
        };

        assertArrayEquals(expected, observed);
    }

    @Test
    void compareArrayLists() {
        // Arrange.
        List<Integer> numbers_1 = new ArrayList<>();
        numbers_1.add(50);
        numbers_1.add(10);
        numbers_1.add(30);

        List<Integer> numbers_2 = new ArrayList<>(
            Arrays.asList(50, 10, 30)
        );

        // Act.
        boolean areEqual = numbers_1.equals(numbers_2);

        // Assert.
        assertTrue(areEqual);
    }

    @Test
    void removeFirstOccurrenceOfValue() {
        // Arrange.
        List<String> animals = new ArrayList<>(
            Arrays.asList("cat", "bird", "cat", "dog")
        );

        // Act.
        animals.remove("cat");

        // Assert.
        List<String> expectedAnimals = new ArrayList<>(
            Arrays.asList("bird", "cat", "dog")
        );
        boolean areEqual = animals.equals(expectedAnimals);
        assertTrue(areEqual);
    }

    @Test
    void sort() {
        // Arrange.
        List<Integer> numbers = new ArrayList<>();

        numbers.add(5);
        numbers.add(1);
        numbers.add(3);

        // Act.
        Collections.sort(numbers);

        Integer[] observedNumbers = numbers.toArray(
            new Integer[0]
        );

        // Assert.
        Integer[] expectedNumbers = {1, 3, 5};
        assertArrayEquals(expectedNumbers, observedNumbers);
    }

    @Test
    void searchInSortedArrayList_1() {
        // Arrange.
        List<Integer> numbers = new ArrayList<>(
            Arrays.asList(0, 10, 20, 30, 40)
        );

        int valueToSearchFor = 30;

        // Act.
        int observed = Collections.binarySearch(numbers, valueToSearchFor);

        // Assert.
        int expected = 3;
        assertEquals(expected, observed);
    }

    @Test
    void searchInSortedArrayList_2() {
        // Arrange.
        List<Integer> numbers = new ArrayList<>(
            Arrays.asList(0, 10, 20, 30, 40)
        );

        int valueToSearchFor = 17;

        // Act.
        int observed = Collections.binarySearch(numbers, valueToSearchFor);

        // Assert.
        assertTrue(observed < 0);
    }

    @Test
    void searchInUnsortedArrayList() {
        // Arrange.
        String[] weekdaysArr = {
            "Monday",
            "Wednesday",
            "Monday",
            "Friday"
        };

        List<String> weekdays = new ArrayList<>(
            Arrays.asList(weekdaysArr)
        );

        // Act.
        int observedIdxOfMonday = weekdays.indexOf("Monday");
        int observedLastIdxOfMonday = weekdays.lastIndexOf("Monday");

        // Assert.
        int expectedIdxOfMonday = 0;
        int expectedLastIdxOfMonday = 2;

        assertEquals(expectedIdxOfMonday, observedIdxOfMonday);
        assertEquals(expectedLastIdxOfMonday, observedLastIdxOfMonday);
    }
}
