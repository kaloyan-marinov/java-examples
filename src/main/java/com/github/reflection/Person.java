package com.github.reflection;

public class Person {
    private final String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int setAge(int age) {
        return age;
    }

    public String publicNonstaticMethod() {
        return "This method is public & non-static.";
    }

    private String privateNonstaticMethod() {
        return "This method is private & non-static.";
    }

    public static String publicStaticMethod() {
        return "This method is public & static.";
    }

    private static String privateStaticMethod() {
        return "This method is private & static.";
    }
}
