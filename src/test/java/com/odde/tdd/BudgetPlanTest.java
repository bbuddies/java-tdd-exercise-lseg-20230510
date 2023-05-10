package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetPlanTest {
    @Test
    void no_budget() {
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());
        BudgetPlan plan = new BudgetPlan(repo);
        assertEquals(0, plan.query(LocalDate.of(2023, 4, 20), LocalDate.of(2023, 6, 4)));
    }

    @Test
    void single_middle_month_budget() {
        BudgetRepo repo = mock(BudgetRepo.class);
        List<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget(YearMonth.of(2023,5),3100));
        budgets.add(new Budget(YearMonth.of(2023,6),300));
        budgets.add(new Budget(YearMonth.of(2023,7),310));
        budgets.add(new Budget(YearMonth.of(2023,8),3100));
        when(repo.findAll()).thenReturn(budgets);
        BudgetPlan plan = new BudgetPlan(repo);
        assertEquals(1540.0, plan.query(LocalDate.of(2023, 5, 20), LocalDate.of(2023, 7, 4)));
    }

    @Test
    void multiple_middle_months_budget() {
        BudgetRepo repo = mock(BudgetRepo.class);
        List<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget(YearMonth.of(2023,5),3100));
        budgets.add(new Budget(YearMonth.of(2023,6),300));
        budgets.add(new Budget(YearMonth.of(2023,7),310));
        budgets.add(new Budget(YearMonth.of(2023,8),3100));
        when(repo.findAll()).thenReturn(budgets);
        BudgetPlan plan = new BudgetPlan(repo);
        assertEquals(2210.0, plan.query(LocalDate.of(2023, 5, 20), LocalDate.of(2023, 8, 4)));
    }
}