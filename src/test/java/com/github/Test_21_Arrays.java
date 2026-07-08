package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Test_21_Arrays {
    @Test
    void sortArray() {
        // Arrange.
        int[] integers = new int[] {5, 2, 1, 4, 8};

        // Act.
        Arrays.sort(integers);

        // Assert.
        int[] expected = new int[] {1, 2, 4, 5, 8};
        assertArrayEquals(expected, integers);
    }

    @Test
    void sortSpecificPortionOfArray() {
        // Arrange.
        String[] sections = new String[] {
            "Introduction",
            "Section C",
            "Section A",
            "Section B",
            "Conclusion"
        };

        // Act.
        Arrays.sort(sections, 1, 4);

        // Assert.
        String[] expected = new String[] {
            "Introduction",
            "Section A",
            "Section B",
            "Section C",
            "Conclusion"
        };
        assertArrayEquals(expected, sections);
    }

    @Test
    void searchInSortedArray_1() {
        // Arrange.
        int[] numbers = new int[] {0, 10, 20, 30, 40};

        int valueToSearchFor = 30;

        // Act.
        int observed = Arrays.binarySearch(numbers, valueToSearchFor);

        // Assert.
        int expected = 3;
        assertEquals(expected, observed);
    }

    @Test
    void searchInSortedArray_2() {
        // Arrange.
        int[] numbers = new int[] {0, 5, 10};

        int valueToSearchFor = 17;

        // Act.
        int observed = Arrays.binarySearch(numbers, valueToSearchFor);

        // Assert.
        assertTrue(observed < 0);
    }

    @Test
    void compareArrays() {
        // Arrange.
        String[] names_1 = new String[] {"Alice", "Bob", "Charlie"};
        String[] names_2 = new String[] {"Alice", "Bob", "Charlie"};

        // Act.
        boolean areEqual = Arrays.equals(names_1, names_2);

        // Assert.
        assertTrue(areEqual);
    }

    @Test
    void createElementWhereAllElementsHoldTheSameValue() {
        // Arrange.
        String[] names = new String[3];

        // Act.
        Arrays.fill(names, "John Doe");

        // Assert.
        String[] observed = new String[] {
            "John Doe", 
            "John Doe", 
            "John Doe"
        };

        assertArrayEquals(names, observed);
    }

    @Test
    void createCopyGivenRangeOfIndices() {
        // Arrange.
        int[] numbers = new int[] {0, 100, 200, 300, 400};

        int startIdx = 1;
        int finalIdx = 4;

        // Act.
        int[] expected = Arrays.copyOfRange(numbers, startIdx, finalIdx);

        // Assert.
        int[] observed = new int[] {100, 200, 300};
        assertArrayEquals(expected, observed);
    }

    @Test
    void createCopyGivenTargetSize_1() {
        // Arrange.
        int[] numbers = new int[] {0, -1, -2, -3, -4};

        int targetSize = 3;

        // Act.
        int[] expected = Arrays.copyOf(numbers, targetSize);

        // Assert.
        int[] observed = new int[] {0, -1, -2};
        assertArrayEquals(expected, observed);
    }

    @Test
    void createCopyGivenTargetSize_2() {
        // Arrange.
        Integer[] numbers = new Integer[] {0, -10, -20};

        int targetSize = numbers.length + 2;

        // Act.
        Integer[] expected = Arrays.copyOf(numbers, targetSize);

        // Assert.
        Integer[] observed = new Integer[] {0, -10, -20, null, null};
        assertArrayEquals(expected, observed);
    }
}
