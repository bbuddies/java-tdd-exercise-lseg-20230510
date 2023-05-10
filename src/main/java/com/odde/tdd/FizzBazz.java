package com.odde.tdd;

public class FizzBazz {

    public static String fizzbass(int i) {
        if (i <= 0) {
            return "";
        }
        return (i % 3 == 0 ? "Fizz" : "") + (i % 5 == 0 ? "Bazz" : "");
    }
}
