package com.odde.tdd;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class BudgetPlan {
    private BudgetRepo budgetRepo;

    public BudgetPlan(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate startDate, LocalDate endDate) {
        double result = 0;
        YearMonth currentStart = YearMonth.of(startDate.getYear(), startDate.getMonth());
        YearMonth currentEnd = YearMonth.of(endDate.getYear(), endDate.getMonth());
        if (currentStart.compareTo(currentEnd) > 0) {
            return result;
        }

        List<Budget> budgets = budgetRepo.findAll(); //assume budgets is sorted
        if (budgets.isEmpty()) {
            return result;
        }

        for (int i = 0; i < budgets.size(); i++) {
            Budget budget = budgets.get(i);
            if (budget.getMonth().isBefore(currentStart)) {
                continue;
            }
            int monthDays = currentStart.lengthOfMonth();
            double monthBudget = budget.getAmount();

            if (budget.getMonth().isAfter(currentStart)) {
                if (budget.getMonth().isAfter(currentEnd)) {
                    return result;
                }
                else {
                    currentStart = budget.getMonth();
                    startDate = LocalDate.of(currentStart.getYear(), currentStart.getMonth(), 1);
                }
            }
            if (budget.getMonth().equals(currentStart)) {
                if (budget.getMonth().equals(currentEnd)) {
                    long durationDays = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays() + 1;
                    result += monthBudget * durationDays / monthDays;
                    break;
                } else {
                    LocalDate nextStartDate = LocalDate.of(currentStart.getYear(), currentStart.plusMonths(1).getMonth(), 1);
                    long durationDays = Duration.between(startDate.atStartOfDay(), nextStartDate.atStartOfDay()).toDays();
                    result += monthBudget * durationDays / monthDays;
                    startDate = nextStartDate;
                    currentStart = currentStart.plusMonths(1);
                }
            }
        }
        return result;
    }


}
