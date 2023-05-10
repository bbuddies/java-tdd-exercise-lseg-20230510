package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class BudgetService {

    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public int budget(LocalDate from, LocalDate to) {

        if(from.isAfter(to)){
            return 0;
        }
        List<Budget> budgets = budgetRepo.findAll();
        budgets.sort(Comparator.comparing(Budget::getMonth));
        if (from.getMonth() == to.getMonth() && from.getYear() == to.getYear()) {
            return budgets.stream()
                    .filter(budget -> budget.getMonth().getYear() == from.getYear() && budget.getMonth().getMonth() == from.getMonth())
                    .findFirst()
                    .map(budget ->
                            (int) (budget.getAmount() * (DAYS.between(from, to) + 1) / budget.getMonth().lengthOfMonth())
                    ).orElse(0);
        }
        return budgets.stream()
                .filter(budget -> from.getMonth() == to.getMonth()
                        || from.getYear() == to.getYear()
                        || (budget.getMonth().isAfter(YearMonth.from(from)) && budget.getMonth().isBefore(YearMonth.from(to)))).map(budget -> {
                    if (budget.getMonth().equals(YearMonth.from(from))) {
                        return (int) (budget.getAmount() * (budget.getMonth().lengthOfMonth() - from.getDayOfMonth() + 1) / budget.getMonth().lengthOfMonth());
                    }
                    if (budget.getMonth().equals(YearMonth.from(to))) {
                        return (int) (budget.getAmount() * to.getDayOfMonth() / budget.getMonth().lengthOfMonth());
                    }
                    return (int)budget.getAmount();
                }).reduce(Integer::sum).orElse(0);


    }
}
