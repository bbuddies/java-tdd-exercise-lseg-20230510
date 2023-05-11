package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


public class BudgetService {
    private final BudgetRepo budgetRepo;
    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public int budget(Period period) {
        if (period.getFrom().isAfter(period.getTo())) {
            return 0;
        }
        YearMonth fromMonth = YearMonth.from(period.getFrom());
        YearMonth toMonth = YearMonth.from(period.getTo());
        List<Budget> budgets = budgetRepo.findAll();
        if (fromMonth.equals(toMonth)) {
            return budgets.stream()
                    .filter(budget -> budget.getMonth().equals(fromMonth))
                    .findFirst()
                    .map(budget ->
                            getAmountOfPeriod(new Period(period.getFrom(), period.getTo()), budget)
                    ).orElse(0);
        }
        return budgets.stream()
                .filter(budget -> budget.getMonth().equals(fromMonth)
                        || budget.getMonth().equals(toMonth)
                        || (budget.getMonth().isAfter(fromMonth) && budget.getMonth().isBefore(toMonth))).map(budget -> {
                    if (budget.getMonth().equals(fromMonth)) {
                        LocalDate endOfBudget = budget.getMonth().atEndOfMonth();
                        return getAmountOfPeriod(new Period(period.getFrom(), endOfBudget), budget);
                    }
                    if (budget.getMonth().equals(toMonth)) {
                        LocalDate startOfBudget = budget.getMonth().atDay(1);
                        return getAmountOfPeriod(new Period(startOfBudget, period.getTo()), budget);
                    }
                    return (int) budget.getAmount();
                }).reduce(Integer::sum).orElse(0);
    }

    private int getAmountOfPeriod(Period period, Budget budget) {
        return (int) (budget.getAmount() * period.getDays() / budget.getDays());
    }

}
