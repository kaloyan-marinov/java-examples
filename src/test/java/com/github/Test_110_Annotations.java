package com.github;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.annotations.Cat;
import com.github.annotations.AnnotationForField;
import com.github.annotations.AnnotationNotParameterizedForMethod;
import com.github.annotations.AnnotationParameterizedForMethod;
import com.github.annotations.AnnotationForClass;

// TODO: (2026/07/14, 21:07)
//      find out why the `Exclamation` class does not need to be imported explicitly

/**
 * 
 * what exactly is an annotation?
 * 
 * Annotations are a kind of metadata.
 * They're supplemental information that you can put into your Java code.
 * They don't directly affect the code that you annotate
 * but those annotations can be processed by something else such as
 * by the compiler
 * or, at runtime. with some code (that uses the Java Reflection API and) that you write yourself.
 * 
 * Annotations can be added to just about anything in Java, so that means:
 * classes,
 * variables
 * methods,
 * method parameters,
 * and even other annotations.
 * 
 * You'll always put your annotation right before the thing that you want to annotate.
 */
public class Test_110_Annotations {
    
    @Test
    void checkClassForSpecificAnnotation_1() {
        // Arrange.
        Cat myCat = new Cat("Stella");

        // Act.
        boolean observed =
            myCat
            .getClass()
            .isAnnotationPresent(AnnotationForClass.class)
        ;

        // Assert.
        boolean expected = true;
        assertEquals(expected, observed);
    }
    
    @Test
    void checkClassForSpecificAnnotation_2() {
        // Arrange.
        Exclamation exclamation = new Exclamation();

        // Act.
        boolean observed =
            exclamation
            .getClass()
            .isAnnotationPresent(AnnotationForClass.class)
        ;

        // Assert.
        boolean expected = false;
        assertEquals(expected, observed);
    }

    @Test
    void processAnnotationForField() {
        // Arrange.
        Cat myCat = new Cat("Stella");

        // Act.
        List<String> observedValuesList = new ArrayList<>();

        for (Field fld : myCat.getClass().getDeclaredFields()) {
            if (fld.isAnnotationPresent(AnnotationForField.class)) {

                Object observed = null;

                try {
                    observed = fld.get(myCat);
                } catch (IllegalAccessException e) {
                    fail("Encountered IllegalAccessException : " + e.getStackTrace());
                }

                /*
                if (observed instanceof String) {
                    observedValuesList.add(
                        ((String) observed).toUpperCase()
                    );                    
                }
                */
                if (observed instanceof String observedAsString) {
                    observedValuesList.add(
                        observedAsString.toUpperCase()
                    );
                }
            }
        }

        String[] observedValues = observedValuesList.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedValues = {"STELLA"};
        assertArrayEquals(
            expectedValues,
            observedValues
        );

    }

    @Test
    void processAnnotationThatIsNotParameterized() {
        // Arrange.
        Cat myCat = new Cat("Stella");

        // Act.
        List<String> observedValuesList = new ArrayList<>();

        for (Method mthd : myCat.getClass().getDeclaredMethods()) {
            if (mthd.isAnnotationPresent(AnnotationNotParameterizedForMethod.class)) {

                Object observed = null;

                try {
                    observed = mthd.invoke(myCat);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
                }

                if (mthd.getReturnType() != String.class) {
                    fail("mthd does not return a String");
                }

                observedValuesList.add((String) observed);
            }
        }

        String[] observedValues = observedValuesList.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedValues = {"Meow!"};
        assertArrayEquals(
            expectedValues,
            observedValues
        );
    }

    @Test
    void processAnnotationThatIsParameterized() {
        // Arrange.
        Cat myCat = new Cat("Stella");

        // Act.
        List<String> observedValuesList = new ArrayList<>();
        
        for (Method mthd : myCat.getClass().getDeclaredMethods()) {
            if (mthd.isAnnotationPresent(AnnotationParameterizedForMethod.class)) {

                Object observed = null;

                AnnotationParameterizedForMethod annotation = mthd.getAnnotation(AnnotationParameterizedForMethod.class);

                for (int i = 0; i < annotation.times(); i++) {
                    try {
                        observed = mthd.invoke(myCat);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        fail("encountered an IllegalAccessException | InvocationTargetException : " + e.getStackTrace());
                    }
    
                    if (mthd.getReturnType() != String.class) {
                        fail("mthd does not return a String");
                    }
    
                    observedValuesList.add((String) observed);
                }
            }
        }

        String[] observedValues = observedValuesList.toArray(
            new String[0]
        );

        // Assert.
        String[] expectedValues = {
            "[A `Cat` is eating.]",
            "[A `Cat` is eating.]",
            "[A `Cat` is eating.]"
        };
        assertArrayEquals(
            expectedValues,
            observedValues
        );
    }
}
