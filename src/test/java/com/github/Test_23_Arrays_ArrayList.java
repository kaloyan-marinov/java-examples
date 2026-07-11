package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_23_Arrays_ArrayList {
    @Test
    void asList() {
        // Arrange.
        String[] namesArr = {"Alice", "Bob", "Charlie"};

        // Act.
        // (
        //      as emphasized on https://www.baeldung.com/java-util-arrays#2aslist :
        //
        //      Note ... that, curiously, `java.util.Arrays` has its own `ArrayList` [class], which `asList` returns.
        //      This can be very deceptive when debugging!
        //
        //      ---
        //
        //      the source code of `Arrays.class` reveals that
        //      the above-mentioned `ArrayList` class is a `private static class`, which is nested inside `Arrays`
        // )
        List<String> namesList = Arrays.asList(namesArr);
        
        String observedRuntimeClass = namesList.getClass().getName();

        // Assert.

        // weak: compile-time generic type
        assertInstanceOf(List.class, namesList);

        // strong: exact runtime class by name
        String expectedRuntimeClass = "java.util.Arrays$ArrayList";
        assertEquals(expectedRuntimeClass, observedRuntimeClass);
    }
}
