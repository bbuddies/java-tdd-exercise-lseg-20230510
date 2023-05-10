package com.odde.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {
    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void normal_number() {
        assertEquals("1", fizzBuzz.say(1));
        assertEquals("2", fizzBuzz.say(2));
    }

    @Test
    void multiples_of_3() {
        assertEquals("Fizz", fizzBuzz.say(3));
        assertEquals("Fizz", fizzBuzz.say(6));
    }

    @Test
    void multiples_of_5() {
        assertEquals("Buzz", fizzBuzz.say(5));
        assertEquals("Buzz", fizzBuzz.say(10));
    }

    @Test
    void contains_3() {
        assertEquals("Fizz", fizzBuzz.say(13));
        assertEquals("Fizz", fizzBuzz.say(23));
    }

    @Test
    void contains_5() {
        assertEquals("Buzz", fizzBuzz.say(52));
        assertEquals("Buzz", fizzBuzz.say(59));
    }

    @Test
    void fizzbuzz() {
        assertEquals("FizzBuzz", fizzBuzz.say(15));
        assertEquals("FizzBuzz", fizzBuzz.say(51));
        assertEquals("FizzBuzz", fizzBuzz.say(130));
        assertEquals("FizzBuzz", fizzBuzz.say(53));
    }
}