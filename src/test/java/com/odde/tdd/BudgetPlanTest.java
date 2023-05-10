package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

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
}