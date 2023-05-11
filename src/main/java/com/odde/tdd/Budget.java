package com.odde.tdd;

import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }

    public YearMonth getMonth() {
        return month;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "month=" + month +
                ", amount=" + amount +
                '}';
    }

    int getDays() {
        return month.lengthOfMonth();
    }

    private Period getPeriod() {
        return new Period(month.atDay(1), month.atEndOfMonth());
    }

    int getOverlappingAmount(Period period) {
        return (int) (amount * period.getOverlappingDays(getPeriod()) / getDays());
    }
}
