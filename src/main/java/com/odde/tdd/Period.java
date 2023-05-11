package com.odde.tdd;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate from;
    private final LocalDate to;

    public Period(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    long getDays() {
        return DAYS.between(from, to) + 1;
    }
}
