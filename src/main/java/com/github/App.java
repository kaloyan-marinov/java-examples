package com.github;

public class App {
    public static void main(String[] args) {
        String word = args.length > 0 ? args[0] : null;

        if (word == null) {
            throw new NullPointerException("'word' cannot be null");
        }

        String exclamation = Exclamation.build(word);

        System.out.println(exclamation);
    }
}
