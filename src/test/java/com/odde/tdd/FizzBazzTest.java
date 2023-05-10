package com.odde.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBazzTest {

    @Test
    void testFizz() {
        testFizzBazz(3,"Fizz");
        testFizzBazz(23,"Fizz");
    }

    private void testFizzBazz(int i, String expected) {
        //Given
        i = 3;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        expected = "Fizz";
        assertEquals(expected, actual);
    }

    @Test
    void testBazz() {

        testFizzBazz(5,"Bazz");
        testFizzBazz(51,"Bazz");
    }

    @Test
    void testFizzBazz() {

        testFizzBazz(15,"FizzBazz");
        testFizzBazz(30,"FizzBazz");

    }

    @Test
    void testNormalNumber() {

        testFizzBazz(16,"16");
        testFizzBazz(19,"19");
    }

    @Test
    void testZero() {
        testFizzBazz(0,"0");
    }



}