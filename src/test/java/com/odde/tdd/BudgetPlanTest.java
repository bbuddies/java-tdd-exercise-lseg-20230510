package com.odde.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetPlanTest {
    private BudgetRepo budgetRepo;
    private BudgetPlan budgetPlan;

    @BeforeEach
    void init() {
        budgetRepo = mock(BudgetRepo.class);
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2023, 1), 1000L));
        budgetList.add(new Budget(YearMonth.of(2023, 2), 400L));
        budgetList.add(new Budget(YearMonth.of(2023, 3), 3100L));
        budgetList.add(new Budget(YearMonth.of(2023, 4), 2000L));
        when(budgetRepo.findAll()).thenReturn(budgetList);

        budgetPlan = new BudgetPlan(budgetRepo);
    }


    @Test
    void end_before_budgets() {
        assertEquals(0, budgetPlan.query(LocalDate.of(2022, 11, 1), LocalDate.of(2022, 12, 1)));
    }

    @Test
    void start_after_budgets() {
        assertEquals(0, budgetPlan.query(LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 11)));
    }

    @Test
    void budget_1_month() {
        assertEquals(400L, budgetPlan.query(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 28)));
    }

    @Test
    void budget_less_in_1_month() {
        assertEquals(1100, budgetPlan.query(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 11)));
    }

    @Test
    void budget_cross_1_month() {
        assertEquals(14 * 400D/28 + 11 * 3100D/31, budgetPlan.query(LocalDate.of(2023, 2, 15), LocalDate.of(2023, 3, 11)));
    }

    @Test
    void budget_cross_2_month() {
        assertEquals(14 * 400D/28 + 3100 + 11 * 2000D/30, budgetPlan.query(LocalDate.of(2023, 2, 15), LocalDate.of(2023, 4, 11)));
    }

    @Test
    void start_before_budget() {
        assertEquals(13 * 1000D/31, budgetPlan.query(LocalDate.of(2022, 12, 18), LocalDate.of(2023, 1, 13)));
    }

    @Test
    void start_before_budget_cross_month() {
        assertEquals(13 * 400D/28 + 1000, budgetPlan.query(LocalDate.of(2022, 12, 18), LocalDate.of(2023, 2, 13)));
    }

    @Test
    void end_after_budget() {
        assertEquals(9 * 2000D/30, budgetPlan.query(LocalDate.of(2023, 4, 22), LocalDate.of(2023, 5, 19)));
    }

    @Test
    void end_after_budget_cross_month() {
        assertEquals(10 * 3100D/31 + 2000, budgetPlan.query(LocalDate.of(2023, 3, 22), LocalDate.of(2023, 5, 19)));
    }

    @Test
    void start_after_end() {
        assertEquals(0, budgetPlan.query(LocalDate.of(2023, 4, 22), LocalDate.of(2023, 3, 19)));
    }

    @Test
    void no_budget() {
        when(budgetRepo.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, budgetPlan.query(LocalDate.of(2023, 3, 22), LocalDate.of(2023, 4, 19)));
    }
}