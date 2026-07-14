package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_41_HashSet {
    private Set<String> planets;

    @BeforeEach
    void setUp() {
        planets = new HashSet<>();

        planets.add("Mercury");
        planets.add("Venus");
        planets.add("Earth");
        planets.add("Mars");
    }

    @Test
    void size() {
        // Act.
        int observedCount = planets.size();

        // Assert.
        int expectedCount = 4;
        assertEquals(expectedCount, observedCount);
    }

    @Test
    void contains() {
        // Act.
        boolean containsMars = planets.contains("Mars");
        boolean containsJupiter = planets.contains("Jupiter");

        // Assert.
        assertTrue(containsMars);
        assertFalse(containsJupiter);;
    }

    @Test
    void remove() {
        // Act.
        planets.remove("Mercury");

        // Assert.
        boolean containsMercury = planets.contains("Mercury");
        assertFalse(containsMercury);
    }

    @Test
    void isEmpty() {
        // Act.
        boolean isEmpty = planets.isEmpty();

        // Assert.
        assertFalse(isEmpty);
    }

    @Test
    void clear() {
        // Act.
        planets.clear();

        int observedSize = planets.size();

        // Assert.
        int expectedSize = 0;
        assertEquals(expectedSize, observedSize);

        assertTrue(planets.isEmpty());
    }

    /**
     * https://www.javathinking.com/blog/copying-sets-in-java/#using-the-copy-constructor
     * 
     * If the elements are mutable objects,
     * changes to the elements in one `Set` will be reflected in the other `Set`.
     */
    @Test
    void createUsingCopyConstructor() {
        // Act.
        Set<String> planetNames = new HashSet<>(planets);

        // Assert
        assertFalse(planets == planetNames);
        
        assertEquals(planets, planetNames);
    }

    @Test
    void createFromArrayListAndMakeComparison() {
        // Arrange.
        List<String> planetNamesRaw = new ArrayList<>(
            Arrays.asList("Mercury", "Venus", "Earth", "Mars")
        );

        // Act.
        Set<String> planetNames = new HashSet<>(planetNamesRaw);

        // Assert
        assertFalse(planets == planetNames);
        
        assertEquals(planets, planetNames);
    }

    @Test
    void createFromArraysArrayListAndMakeComparison() {
        // Act.
        Set<String> planetNames = new HashSet<>(
            Arrays.asList("Mercury", "Venus", "Earth", "Mars")
        );

        // Assert
        assertFalse(planets == planetNames);
        
        assertEquals(planets, planetNames);
    }

    @Test
    void addSameValueMultipleTimes() {
        // Arrange.
        Set<String> planetNames = new HashSet<>(planets);

        // Act.
        planetNames.add("Mercury");

        // Assert.
        assertFalse(planetNames == planets);

        assertEquals(planetNames, planets);
    }

    @Test
    void containsAll() {
        // Arrange.
        Set<String> planetNames = new HashSet<>(planets);

        // Act.
        planetNames.add("Jupiter");

        boolean observedContainsAll = planetNames.containsAll(planets);

        // Assert.
        assertTrue(observedContainsAll);
    }
}
