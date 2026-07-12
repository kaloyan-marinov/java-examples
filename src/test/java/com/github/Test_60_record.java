package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class Test_60_record {
    
    /**
     * a very simple class
     * 
     * all it contains is a bunch of fields to carry data
     * «without exposing functionality for modifying that data»
     * 
     * these kinds of classes are very common in the Java world
    */
    private class EmployeeClass {
        private final String name;
        private final int id;

        public EmployeeClass(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "EmployeeClass(name=" + this.getName() + ", id=" + this.getId() + ")";
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null) {
                return false;
            }

            if (getClass() != o.getClass()) {
                return false;
            }

            EmployeeClass other = (EmployeeClass) o;

            return (
                Objects.equals(name, other.name)
                &&
                Objects.equals(id, other.id)
            );
        }

        public String nameInUppercase() {
            return name.toUpperCase();
        }

        public static String sayHello() {
            return "hello";
        }

        public static final String GOODBYE = "goodbye";
    }

    /**
     * The preceding approach requires a large amount of code
     * for a class whose ultimate purpose is to hold 2 fields.
     * 
     * 
     * 
     * TLDR:
     * -----
     * 
     * Records are immutable data classes that require only the type and name of fields.
     * 
     * 
     * 
     * Details:
     * --------
     * 
     * A `record` is a certain special type of class.
     * 
     * To define a `record`, there is only one real requirement:
     * the programmer must specify the types and names of all fields/components,
     * which are to be held inside the `record`.
     * (Additional things can be done within the definition of a `record`,
     * but all of those are optional.)
     * 
     * With just that, the Java compiler gives us the following things for free:
     * 
     * - it generates `private final` fields for all components specified in the definition/declaration
     * 
     * - it generates `public` "getter" methods
     *   (with the only caveat being that their names do NOT start with the word "get")
     * 
     * - it generates a `public` «canonical» constructor
     *   (that takes as parameters all components specified in the definition/declaration
     *   and sets the `record`'s fields with the values in those parameters)
     * 
     * - it generates implementations of the `toString`, `hashCode`, and `equals` methods
     * 
     * - it does NOT generate "setter" methods
     * 
     * 
     * 
     * Furthermore, Java `record`s
     * 
     * - are implicitly `final` `class`es,
     *   which means that they cannot extended by any other `class`
     *  
     * - implicitly extend the `Record` class
     *   (so, b/c Java doesn't allow multiple inheritance, a Java `record` cannot extend any other `class`)
     * 
     * - can implement `interface`(s)
     */
    private record EmployeeRecord(String name, int id) {

        /*
        // possible to override the «canonical» constructor
        public EmployeeRecord(String name, int id) {
            // Perform validation.
            if (id < 0) {
                throw new IllegalArgumentException("'id' cannot be negative");
            }

            // Set values for the record's fields.
            this.name = name;
            this.id = id;
        }
        */

        /*
        // shortcut for overriding the «canonical» constructor:
        // «compact constructor»
        //
        // this is something that is unique only to `record`s
        public EmployeeRecord {
            // Perform validation.
            if (id < 0) {
                throw new IllegalArgumentException("'id' cannot be negative");
            }
        }
        */

        public String nameInUppercase() {
            return name.toUpperCase();
        }

        public static String sayHello() {
            return "hello";
        }

        public static final String GOODBYE = "goodbye";
    }

    @Test
    void compareAlternatives() {
        // Arrange.
        String name = "John Doe";
        int id = 17;

        // Act.
        EmployeeClass employeeC = new EmployeeClass(name, id);
        EmployeeRecord employeeR = new EmployeeRecord(name, id);

        // Assert.
        assertEquals(
            employeeC.getName(),
            employeeR.name()
        );
        assertEquals(
            employeeC.getId(),
            employeeR.id()
        );

        assertEquals(
            employeeC.toString(),
            "EmployeeClass(name=John Doe, id=17)"
        );
        assertEquals(
            employeeR.toString(),
            "EmployeeRecord[name=John Doe, id=17]"
        );

        assertEquals(
            employeeC.nameInUppercase(),
            employeeR.nameInUppercase()
        );

        String cHello = EmployeeClass.sayHello();
        String rHello = EmployeeRecord.sayHello();
        assertTrue(
            cHello.equals(rHello)
        );

        assertTrue(
            EmployeeClass.GOODBYE.equals(EmployeeRecord.GOODBYE)
        );
    }

    @Test
    void equals() {
        // Arrange.
        String name = "Mary Smith";
        int id = 27;

        EmployeeRecord er1 = new EmployeeRecord(name, id);
        EmployeeRecord er2 = new EmployeeRecord(name, id);

        // Act.
        boolean observedSame = er1 == er2;
        boolean observedEquals = er1.equals(er2);

        // Assert.
        assertFalse(observedSame);
        assertTrue(observedEquals);
    }

    @Test
    void _hashCode() {
        // Arrange.
        String name = "Mary Smith";
        int id = 27;

        EmployeeRecord er1 = new EmployeeRecord(name, id);
        EmployeeRecord er2 = new EmployeeRecord(name, id);

        // Act.
        int hc1 = er1.hashCode();
        int hc2 = er2.hashCode();

        // Assert.
        assertEquals(hc1, hc2);
    }
}
