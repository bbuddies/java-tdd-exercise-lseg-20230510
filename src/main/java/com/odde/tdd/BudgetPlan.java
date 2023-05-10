package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BudgetPlan {
    private final BudgetRepo repo;

    public BudgetPlan(BudgetRepo repo) {
        this.repo = repo;
    }

    public int query(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.findAll();
        if (budgets.isEmpty()) {
            return 0;
        }
        int startYear = start.getYear();
        int startMonth = start.getMonthValue();
        int endYear = end.getYear();
        int endMonth = end.getMonthValue();
        int startAmount = 0;
        int endAmount = 0;
        int sumMiddleAmount = 0;
        for (Budget budget : budgets) {
            YearMonth budgetMonth = budget.getMonth();
            int amount = budget.getAmount();
            if (YearMonth.of(startYear, startMonth).equals(budgetMonth)) {
                LocalDate endOfMonth = start.withDayOfMonth(start.lengthOfMonth());
                long daysUntilEndOfMonth = ChronoUnit.DAYS.between(start, endOfMonth) + 1;
                startAmount = amount / start.lengthOfMonth() * (int)daysUntilEndOfMonth;
            }
            if (YearMonth.of(endYear, endMonth).equals(budgetMonth)) {
                long daysUntilGivenEndDay = ChronoUnit.DAYS.between(end.withDayOfMonth(1), end) + 1;
                endAmount = amount / end.lengthOfMonth() * (int)daysUntilGivenEndDay;
            }
            if (YearMonth.of(startYear, startMonth).isBefore(budgetMonth) &&
                    YearMonth.of(endYear, endMonth).isAfter(budgetMonth)) {
                int middleAmount = budget.getAmount();
                sumMiddleAmount = sumMiddleAmount + middleAmount;
            }
        }
        return startAmount + endAmount + sumMiddleAmount;
    }
}
