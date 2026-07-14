package com.github.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunMultipleTimes {
    /*
     * These parameters can only be:
     * - a primitive type
     * - a class type
     * - a `String`
     * - an array of any of those
     * 
     * Such a parameter can be provided with a default value.
     */
    int times();
}
