package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_80_ReadAFileWithBufferedReader {

    private final Path path = Paths.get("src/test/resources/hello-world.txt");

    @Test
    void readLineByLine() {
        // Act.
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] observedLines = lines.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedLines = {
            "hello",
            "world",
            "!"
        };

        assertArrayEquals(expectedLines, observedLines);
    }

    @Test
    void readTheWholeFileAtOnceIntoList() {
        // Act.
        List<String> lines;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();

            lines = null;
        }

        if (lines == null) {
            fail("failed to read " + path);
        }

        String[] observedLines = lines.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedLines = {
            "hello",
            "world",
            "!"
        };

        assertArrayEquals(expectedLines, observedLines);
    }

    @Test
    void readTheWholeFileAtOnceIntoString() {
        // Act.
        String observedContent;

        try {
            observedContent = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();

            observedContent = null;
        }

        if (observedContent == null) {
            fail("failed to read " + path);
        }

        // Assert.
        String expectedContent = (
            "hello" + "\n"
            + "world"  + "\n"
            + "!"
            + "" + "\n"
        );

        boolean areEqual = expectedContent.equals(observedContent);
        assertTrue(areEqual);
    }
}
