package com.odde.tdd;

public class FizzBazz {

    public static String fizzbass(int i) {
        if (i <= 0) {
            return "" + i;
        }
        StringBuffer sb = new StringBuffer();

        if (i % 3 == 0 || String.valueOf(i).contains("3")) {
            sb.append("Fizz");
        }
        if (i % 5 == 0 || String.valueOf(i).contains("5")) {
            sb.append("Bazz");
        }

        return sb.length() == 0 ? (i + "") : sb.toString();
    }
}
