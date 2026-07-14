package com.github.annotations;

@AnnotationForClass
public class Cat {

    @AnnotationForField
    // private String name;  // causes `processAnnotationOnField` to fail
    public String name;
    private int age;

    public Cat(String name) {
    // public Cat(String name, int age) {
        this.name = name;
        // this.age = age;
    }

    @AnnotationNotParameterizedForMethod
    public String meow() {
        return "Meow!";
    }

    @AnnotationParameterizedForMethod(times = 3)
    public String eat() {
        return "[A `Cat` is eating.]";
    }
}
