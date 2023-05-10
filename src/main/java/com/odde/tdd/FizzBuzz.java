package com.odde.tdd;

public class FizzBuzz {
    public String say(int number) {
        StringBuilder result = new StringBuilder();
        if (number % 3 == 0 || Integer.toString(number).contains("3")) {
            result.append("Fizz");
        }
        if (number % 5 == 0 || Integer.toString(number).contains("5")) {
            result.append("Buzz");
        }
        if (result.length() == 0) {
            result.append(number);
        }
        return result.toString();
    }
}
