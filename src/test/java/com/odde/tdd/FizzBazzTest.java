package com.odde.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBazzTest {

    @Test
    void test1() {
        //Given
        int i = 3;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        String expected = "Fizz";
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        //Given
        int i = 5;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        String expected = "Bazz";
        assertEquals(expected, actual);
    }

    @Test
    void test3() {
        //Given
        int i = 15;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        String expected = "FizzBazz";
        assertEquals(expected, actual);
    }

    @Test
    void test4() {
        //Given
        int i = 16;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void test5() {
        //Given
        int i = 0;
        //When
        String actual = FizzBazz.fizzbass(i);
        //Then
        String expected = "";
        assertEquals(expected, actual);
    }

}