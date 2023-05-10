package com.odde.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
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
        Budget b1 = new Budget(YearMonth.of(2023, 1), 1000L);
        Budget b2 = new Budget(YearMonth.of(2023, 2), 400L);
        Budget b3 = new Budget(YearMonth.of(2023, 3), 3100L);
        Budget b4 = new Budget(YearMonth.of(2023, 4), 2000L);
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(b1);
        budgetList.add(b2);
        budgetList.add(b3);
        budgetList.add(b4);
        when(budgetRepo.findAll()).thenReturn(budgetList);

        budgetPlan = new BudgetPlan(budgetRepo);
    }


    @Test
    void no_budget() {
        assertEquals(0, budgetPlan.query(LocalDate.of(2022, 11, 1), LocalDate.of(2022, 12, 1)));
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
}