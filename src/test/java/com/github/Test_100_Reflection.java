package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.github.reflection.Person;

/**
 * Java's Reflection API is a set of features in the Java language
 * that enable programs to examine and change basically any element of any Java class
 * at runtime (:= in the middle of your running program).
 * 
 * 
 * 
 * When should you use reflection and when should you not?
 * 
 * A good rule of thumb is:
 * if you can possibly do something without reflection,
 * it's best to avoid using reflection
 * (your code will be faster, more robust, more testable)
 * 
 * 
 * 
 * Why would you use this?
 * 
 * [reasons - in order from most subjective to most objective]
 * 
 * - it's just really cool
 * 
 * - helps build up an understanding about how some different parts of Java work
 * 
 * - occasionally when you are testing some class,
 *   that class will have a private field that changes how all of the methods inside that class work;
 *   for testing you want to be able to set that private field
 *   and then make sure all the methods do what they're supposed to do;
 *   in situations like that,
 *   you can use reflection to force Java to set a suitable value for the private field in question;
 * 
 * - it is heavily used in things like frameworks
 * 
 *   for example
 * 
 *   - the Spring Framework uses reflection all over the place
 *     b/c it has to be compatible with code that hasn't even been written yet;
 * 
 *   - when you use a tool like Spring,
 *     it uses reflection to look at all the classes that you've written in your own code
 *     and
 *     also uses reflection to create objects of those classes and
 *     manipulate them and inject them into other classes;
 * 
 *   - all that stuff is way more complex than what the current file does
 *     but it uses the same basic tools
 * 
 * 
 * 
 * There are definnitely downsides of reflection too
 * 
 * - b/c it has to figure out and manipulate stuff at runtime,
 *   it can't do any compile-time optimizations
 * 
 *   so code that uses reflection is usually way slower
 *   than
 *   code that does the same without using reflection
 * 
 * - it's way easy to break everything using reflection
 * 
 *   if you don't know what you're doing,
 *   you can create4 some really wacky bugs that are really, really tough to nail down
 * 
 *   that may cause the code
 *   to quietly continue working (i.e. without crashing)
 *   but in a completely different way
 */
public class Test_100_Reflection {

    @Test
    void _getClass() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        // Act.
        Object observedClass = johnDoe.getClass();

        // Assert.
        Object expectedClass = Person.class;
        assertEquals(expectedClass, observedClass);
    }

    @Test
    void getDeclaredFields() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        // Act.
        Field[] fields = johnDoe.getClass().getDeclaredFields();
        
        Set<String> observedFieldNames = new HashSet<>();
        for (Field fld : fields) {
            observedFieldNames.add(fld.getName());
        }

        // Assert.
        Set<String> expectedFieldNames = new HashSet<>(
            Arrays.asList("name", "age")
        );

        boolean areEqual = expectedFieldNames.equals(observedFieldNames);
        assertTrue(areEqual);

        assertFalse(
            expectedFieldNames == observedFieldNames
        );
    }

    @Test
    void mutateValueOfPrivateFinalField() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        Field nameField = null;
        Field[] fields = johnDoe.getClass().getDeclaredFields();
        for (Field fld : fields) {
            if (fld.getName().equals("name")) {
                nameField = fld;
                break;
            }
        }

        if (nameField == null) {
            fail("nameField is null");
        }

        // Act.
        nameField.setAccessible(true);

        try {
            nameField.set(johnDoe, "Mary Smith");
        } catch (IllegalAccessException e) {
            fail("encountered an IllegalAccessException : " + e.getStackTrace());
        }

        // Assert.
        String expectedName = "Mary Smith";
        assertEquals(
            expectedName,
            johnDoe.getName()
        );
    }

    @Test
    void getDeclaredMethods() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        // Act.
        Method[] methods = johnDoe.getClass().getDeclaredMethods();
        
        Set<String> observedMethodNames = new HashSet<>();
        for (Method mthd : methods) {
            observedMethodNames.add(mthd.getName());
        }

        // Assert.
        Set<String> expectedMethodNames = new HashSet<>(
            Arrays.asList(
                "getName",
                "getAge",
                "setAge",
                "publicNonstaticMethod",
                "privateNonstaticMethod",
                "publicStaticMethod",
                "privateStaticMethod"
            )
        );

        boolean areEqual = expectedMethodNames.equals(observedMethodNames);
        assertTrue(areEqual);

        assertFalse(
            expectedMethodNames == observedMethodNames
        );
    }

    @Test
    void invokePublicNonstaticMethod() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        Method publicNonstaticMethod = null;
        Method[] methods = johnDoe.getClass().getDeclaredMethods();
        for (Method mthd : methods) {
            if (mthd.getName().equals("publicNonstaticMethod")) {
                publicNonstaticMethod = mthd;
            }
        }

        if (publicNonstaticMethod == null) {
            fail("publicNonstaticMethod is null");
        }

        // Act.
        Object observedFromPublicNonstatic = null;
        try {
            observedFromPublicNonstatic = publicNonstaticMethod.invoke(johnDoe);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
        }

        Class<?> returnTypeFromPublic = publicNonstaticMethod.getReturnType();
        if (returnTypeFromPublic != String.class) {
            fail("publicNonstaticMethod does not return a String");
        }

        // Assert.
        assertEquals(
            "This method is public & non-static.",
            (String) observedFromPublicNonstatic
        );
    }

    @Test
    void invokePrivateNonstaticMethod() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        Method privateNonstaticMethod = null;
        Method[] methods = johnDoe.getClass().getDeclaredMethods();
        for (Method mthd : methods) {
            if (mthd.getName().equals("privateNonstaticMethod")) {
                privateNonstaticMethod = mthd;
            }
        }

        if (privateNonstaticMethod == null) {
            fail("privateNonstaticMethod is null");
        }

        // Act.
        privateNonstaticMethod.setAccessible(true);

        Object observedFromPrivateNonstatic = null;
        try {
            observedFromPrivateNonstatic = privateNonstaticMethod.invoke(johnDoe);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
        }

        Class<?> returnTypeFromPrivate = privateNonstaticMethod.getReturnType();
        if (returnTypeFromPrivate != String.class) {
            fail("privateNonstaticMethod does not return a String");
        }

        // Assert.
        assertEquals(
            "This method is private & non-static.",
            (String) observedFromPrivateNonstatic
        );
    }

    @Test
    void invokePublicStaticMethod() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        Method publicStaticMethod = null;
        Method[] methods = johnDoe.getClass().getDeclaredMethods();
        for (Method mthd : methods) {
            if (mthd.getName().equals("publicStaticMethod")) {
                publicStaticMethod = mthd;
            }
        }

        if (publicStaticMethod == null) {
            fail("publicStaticMethod is null");
        }

        // Act.
        Object observedFromPublicStatic = null;
        try {
            observedFromPublicStatic = publicStaticMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
        }

        Class<?> returnTypeFromPublic = publicStaticMethod.getReturnType();
        if (returnTypeFromPublic != String.class) {
            fail("publicStaticMethod does not return a String");
        }

        // Assert.
        assertEquals(
            "This method is public & static.",
            (String) observedFromPublicStatic
        );
    }

    @Test
    void invokePrivateStaticMethod() {
        // Arrange.
        Person johnDoe = new Person("John Doe", 27);

        Method privateStaticMethod = null;
        Method[] methods = johnDoe.getClass().getDeclaredMethods();
        for (Method mthd : methods) {
            if (mthd.getName().equals("privateStaticMethod")) {
                privateStaticMethod = mthd;
            }
        }

        if (privateStaticMethod == null) {
            fail("privateStaticMethod is null");
        }

        // Act.
        privateStaticMethod.setAccessible(true);

        Object observedFromPrivateStatic = null;
        try {
            observedFromPrivateStatic = privateStaticMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
        }

        Class<?> returnTypeFromPrivate = privateStaticMethod.getReturnType();
        if (returnTypeFromPrivate != String.class) {
            fail("privateStaticMethod does not return a String");
        }

        // Assert.
        assertEquals(
            "This method is private & static.",
            (String) observedFromPrivateStatic
        );
    }
}
