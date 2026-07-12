package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Test_50_enum {

    /**
     * a special type of class that
     * implicitly extends the `java.lang.Enum` class
     */
    private enum Weekday {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;
    }

    @Test
    void ifElse() {
        // Arrange.
        Weekday day = Weekday.THURSDAY;

        // Act.
        String observedMessage;

        if (day == Weekday.MONDAY) {
            observedMessage = "Happy Monday!";
        } else if (day == Weekday.TUESDAY || day == Weekday.WEDNESDAY || day == Weekday.THURSDAY) {
            observedMessage = "Gotta keep working...";
        } else if (day == Weekday.FRIDAY) {
            observedMessage = "Happy Friday!";
        } else if (day == Weekday.SATURDAY || day == Weekday.SUNDAY) {
            observedMessage = "Happy weekend!";
        } else {
            observedMessage = null;
        }

        // Assert.
        String expectedMessage = "Gotta keep working...";
        boolean areEqual = expectedMessage.equals(observedMessage);
        assertTrue(areEqual);
    }

    @Test
    void _switch() {
        // Arrange.
        Weekday day = Weekday.THURSDAY;

        // Act.
        String observedMessage;

        switch (day) {
            case Weekday.MONDAY:
                observedMessage = "Happy Monday!";
                break;
            case Weekday.TUESDAY:
            case Weekday.WEDNESDAY:
            case Weekday.THURSDAY:
                observedMessage = "Gotta keep working...";
                break;
            case Weekday.FRIDAY:
                observedMessage = "Happy Friday!";
                break;
            case Weekday.SATURDAY:
            case Weekday.SUNDAY:
                observedMessage = "Happy weekend";
                break;
            default:
                observedMessage = null;
                break;
        }

        // Assert.
        String expectedMessage = "Gotta keep working...";
        boolean areEqual = expectedMessage.equals(observedMessage);
        assertTrue(areEqual);
    }

    private enum Month {
        JANUARY(1, "Jan"),
        FEBRUARY(2, "Feb"),
        MARCH(3, "Mar"),
        APRIL(4, "Apr"),
        MAY(5, "May"),
        JUNE(6, "Jun"),
        JULY(7, "Jul"),
        AUGUST(8, "Aug"),
        SEPTEMBER(9, "Sep"),
        OCTOBER(10, "Oct"),
        NOVEMBER(11, "Nov"),
        DECEMBER(12, "Dec");

        final int number;
        final String abbreviatedName;

        Month(int number, String abbreviatedName) {
            this.number = number;
            this.abbreviatedName = abbreviatedName;
        }

        public int getNumber() {
            return number;
        }

        public String getAbbreviatedName() {
            return abbreviatedName;
        }
    }

    @Test
    void callMethods() {
        // Arrange.
        Month february = Month.FEBRUARY;

        // Act.
        int observedNumber = february.getNumber();
        String observedAbbreviatedName = february.getAbbreviatedName();

        // Assert.
        int expectedNumber = 2;
        String expectedAbbreviatedName = "Feb";

        assertEquals(expectedNumber, observedNumber);
        assertTrue(
            expectedAbbreviatedName.equals(observedAbbreviatedName)
        );
    }
}
