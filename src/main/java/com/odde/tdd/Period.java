package com.odde.tdd;

import org.jetbrains.annotations.NotNull;

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
        return from.isAfter(to) ? 0 : DAYS.between(from, to) + 1;
    }

    @NotNull
    private Period getOverlappingPeriod(Period another) {
        LocalDate startOfOverlapping = from.isAfter(another.from) ? from : another.from;
        LocalDate endOfOverlapping = to.isBefore(another.to) ? to : another.to;
        return new Period(startOfOverlapping, endOfOverlapping);
    }

    long getOverlappingDays(Period another) {
        return getOverlappingPeriod(another).getDays();
    }
}
