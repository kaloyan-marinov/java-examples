package com.github.annotations;

@AnnotationForClass
public class Greeter {

    @AnnotationForField
    // private String name;  // causes `processAnnotationForField` to fail
    public String name;

    private int age;

    public Greeter(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @AnnotationNotParameterizedForMethod
    public String sayHello() {
        return "Hello!";
    }

    @AnnotationParameterizedForMethod(times = 2)
    public String sayBye() {
        return "Bye!";
    }
}
