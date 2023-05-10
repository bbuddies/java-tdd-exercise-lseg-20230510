package com.odde.tdd;

import java.util.ArrayList;
import java.util.List;

public class Prime {
    public List<Integer> factors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();
        for(int candidate = 2;number > Math.sqrt(number); candidate++){
            for (;number % candidate == 0;number /= candidate) {
                factors.add(candidate);
            }
        }
        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}
