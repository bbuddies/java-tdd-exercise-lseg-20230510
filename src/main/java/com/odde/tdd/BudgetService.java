package com.odde.tdd;


public class BudgetService {
    private final BudgetRepo budgetRepo;
    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public int budget(Period period) {
        return budgetRepo.findAll().stream().mapToInt(budget -> budget.getOverlappingAmount(period)).sum();
    }

}
