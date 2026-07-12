package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_70_TryCatchFinally {
    
    private List<String> generateMessages(String input) {
        List<String> messages = new ArrayList<>();

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
