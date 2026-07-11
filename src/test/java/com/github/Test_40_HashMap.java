package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_40_HashMap {
    private Map<String, String> planetToColor;

    @BeforeEach
    void setUp() {
        planetToColor = new HashMap<>();

        planetToColor.put("Mercury", "grey");
        planetToColor.put("Venus", "yellow");
        planetToColor.put("Earth", "blue");
        planetToColor.put("Mars", "red");
    }

    @Test
    void initializeAndPopulate() {
        // Act.
        planetToColor.remove("Mercury");

        boolean observedContainsMercury = planetToColor.containsKey("Mercury");
        boolean observedContainsVenus = planetToColor.containsKey("Venus");
        String observedValueForEarth = planetToColor.get("Earth");
        int observedNumEntries = planetToColor.size();

        // Assert.
        boolean expectedContainsMercury = false;
        boolean expectedContainsVenus = true;
        String expectedValueForEarth = "blue";
        int expectedNumEntries = 3;

        assertEquals(expectedContainsMercury, observedContainsMercury);
        assertEquals(expectedContainsVenus, observedContainsVenus);
        assertEquals(expectedValueForEarth, observedValueForEarth);
        assertEquals(expectedNumEntries, observedNumEntries);
    }

    @Test
    void iterateOverKeys() {
        // Arrange.
        List<String> colors = new ArrayList<>();
        
        // Act.
        String color;
        for (String planet : planetToColor.keySet()) {
            color = planetToColor.get(planet);
            colors.add(color);
        }

        // Assert.
        String[] observedSortedColors = colors.toArray(
            new String[0]
        );
        Arrays.sort(observedSortedColors);

        String[] expectedSortedColors = {
            "blue",
            "grey",
            "red",
            "yellow"
        };

        assertArrayEquals(expectedSortedColors, observedSortedColors);
    }

    @Test
    void iterateOverValues() {
        // Arrange.
        List<String> colors = new ArrayList<>();

        // Act.
        for (String color : planetToColor.values()) {
            colors.add(color);
        }

        // Assert.
        String[] observedSortedColors = colors.toArray(
            new String[0]
        );
        Arrays.sort(observedSortedColors);

        String[] expectedSortedColors = {
            "blue",
            "grey",
            "red",
            "yellow"
        };

        assertArrayEquals(expectedSortedColors, observedSortedColors);
    }

    @Test
    void iterateOverEntries() {
        // Arrange.
        List<String> sentences = new ArrayList<>();

        // Act.
        String sentence;
        for (Map.Entry<String, String> entry : planetToColor.entrySet()) {
            sentence = entry.getKey() + " is " + entry.getValue();
            sentences.add(sentence);
        }

        // Assert.
        String[] observedSentences = sentences.toArray(
            new String[0]
        );
        Arrays.sort(observedSentences);

        String[] expectedSentences = {
            "Earth is blue",
            "Mars is red",
            "Mercury is grey",
            "Venus is yellow"
        };

        assertArrayEquals(expectedSentences, observedSentences);
    }
}
