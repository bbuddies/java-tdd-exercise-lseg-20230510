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
        List<Budget> budgets = budgetRepo.findAll();
        if (budgets.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < budgets.size(); i++) {
            Budget budget = budgets.get(i);
            if (budget.getMonth().getMonth().equals(startDate.getMonth())) {
                double monthBudget = budget.getAmount();
                int monthDays = YearMonth.from(startDate).lengthOfMonth();
                if (budget.getMonth().getMonth().equals(endDate.getMonth())) {
                    long durationDays = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays() + 1;
                    result += monthBudget * durationDays / monthDays;
                    break;
                } else {
                    LocalDate nextStartDate = LocalDate.of(startDate.getYear(), startDate.getMonth().plus(1), 1);
                    long durationDays = Duration.between(startDate.atStartOfDay(), nextStartDate.atStartOfDay()).toDays();
                    result += monthBudget * durationDays / monthDays;
                    startDate = nextStartDate;
                }
            }
        }
        return result;
    }


}
