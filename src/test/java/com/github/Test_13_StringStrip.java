package com.github;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Test_13_StringStrip {

    private String rawGreeting = "\t Hello everyone! ";

    @Test
    void strip() {
        // Act.
        String observed = rawGreeting.strip();

        // Assert.
        String expected = "Hello everyone!";

        boolean areEqual = expected.equals(observed);
        assertTrue(areEqual);
    }

    @Test
    void stripLeading() {
        // Act.
        String observed = rawGreeting.stripLeading();

        // Assert.
        String expected = "Hello everyone! ";

        boolean areEqual = expected.equals(observed);
        assertTrue(areEqual);
    }

    @Test
    void stripTrailing() {
        // Act.
        String observed = rawGreeting.stripTrailing();

        // Assert.
        String expected = "\t Hello everyone!";

        boolean areEqual = expected.equals(observed);
        assertTrue(areEqual);
    }

    @Test
    void stripIndent() {
        // Arrange.
        String html =
            """
            <html>
                <body>
                <p>Hello everyone!</p>
                </body>
            </html>
            """;
        
        // Act.
        String observed = html.stripIndent();

        // Assert.
        String expected =
"""
<html>
    <body>
    <p>Hello everyone!</p>
    </body>
</html>     
"""
        ;

        boolean areEqual = expected.equals(observed);
        assertTrue(areEqual);
    }
}
