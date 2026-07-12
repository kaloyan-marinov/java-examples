package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_70_TryCatchFinally {
    
    private List<String> generateMessages(String input) {
        List<String> messages = new ArrayList<>();

        /**
         * https://www.baeldung.com/java-exceptions#anti-patterns
         * =
         * do NOT swallow `Exception`s
         * 
         * 1) When we catch an `Exception`,
         *    refrain from providing an empty implementation (i.e. simply `{}`) for the `catch` block.
         *    (
         *    There are times when there’s a checked exception that we are confident will just never happen.
         *    In those cases, we should still at least add a comment stating that we intentionally swallowing the exception.
         *    )
         * 
         * 2) When we catch an `Exception`,
         *    we should include it as a cause when throwing a new and/or custom `Exception`.
         * 
         * 3) Do NOT `return` from the `finally` block.
         * 
         *    (
         *    by returning abruptly, the JVM will drop the exception, even if it was thrown from by our code
         *    )
         * 
         * 4) Do NOT `throw` from the `finally` block.
         * 
         *    (
         *    Similar to using `return` in a `finally` block,
         *    the exception thrown in a `finally` block will take precedence over
         *    the exception that arises in [any preceding block of the `try`-`catch`-`finally` construct.
         * 
         *    This will “erase” the original exception from the try block)
         */
        try {
            messages.add("start parsing; input=" + input);

            int valueOfInput = Integer.parseInt(input);

            messages.add("finish parsing; valueOfInput=" + valueOfInput);
        } catch (NumberFormatException nfe) {
            messages.add("encountered NumberFormatException");
        } finally {
            messages.add("performing cleanup/teardown logic");
        }

        return messages;
    }

    @Test
    void parseableInput() {
        // Arrange.
        String seventeen = "17";

        // Act.
        List<String> messages = generateMessages(seventeen);
        
        String[] observedMessages = messages.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedMessages = {
            "start parsing; input=17",
            "finish parsing; valueOfInput=17",
            "performing cleanup/teardown logic"
        };

        assertArrayEquals(expectedMessages, observedMessages);
    }

    @Test
    void unparseableInput() {
        // Arrange.
        String seventeen = "seventeen";

        // Act.
        List<String> messages = generateMessages(seventeen);
        
        String[] observedMessages = messages.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedMessages = {
            "start parsing; input=seventeen",
            "encountered NumberFormatException",
            "performing cleanup/teardown logic"
        };

        assertArrayEquals(expectedMessages, observedMessages);
    }
}
