package com.github.annotations;

@VeryImportant
public class Cat {

    @Important
    // private String name;  // causes `processAnnotationOnField` to fail
    public String name;
    private int age;

    public Cat(String name) {
    // public Cat(String name, int age) {
        this.name = name;
        // this.age = age;
    }

    @RunImmediately
    public String meow() {
        return "Meow!";
    }

    @RunMultipleTimes(times = 3)
    public String eat() {
        return "[A `Cat` is eating.]";
    }
}
