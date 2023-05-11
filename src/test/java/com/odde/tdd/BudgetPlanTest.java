package com.odde.tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetPlanTest {

//    @BeforeAll
//    public void initBudget(){
//
//    }

    @Test
    void no_budget() {
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());
        BudgetPlan plan = new BudgetPlan(repo);
        assertEquals(0, plan.query(LocalDate.of(2023, 4, 20), LocalDate.of(2023, 6, 4)));
    }

    @Test
    public void budgetDaysIn1Month(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(new Budget(YearMonth.of(2023,5), 3100),
                new Budget(YearMonth.of(2023,6), 300),
                new Budget(YearMonth.of(2023,7), 310),
                new Budget(YearMonth.of(2023,8), 3100))
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2023,5,2);
        LocalDate end = LocalDate.of(2023,5,10);
        assertEquals(900, plan.query(start, end));
    }

    @Test
    public void budgetDaysIn2Month(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(new Budget(YearMonth.of(2023,5), 3100),
                        new Budget(YearMonth.of(2023,6), 300),
                        new Budget(YearMonth.of(2023,7), 310),
                        new Budget(YearMonth.of(2023,8), 3100))
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2023,5,2);
        LocalDate end = LocalDate.of(2023,6,2);
        assertEquals(3020, plan.query(start, end));
    }

    /**
     * 3000+300+20
     */
    @Test
    public void budgetDaysIn3Month(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(new Budget(YearMonth.of(2023,5), 3100),
                        new Budget(YearMonth.of(2023,6), 300),
                        new Budget(YearMonth.of(2023,7), 310),
                        new Budget(YearMonth.of(2023,8), 3100))
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2023,5,2);
        LocalDate end = LocalDate.of(2023,7,2);
        assertEquals(3320, plan.query(start, end));
    }

    /**
     * 3000+300+310+200
     */
    @Test
    public void budgetDaysIn4Month(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(new Budget(YearMonth.of(2023,5), 3100),
                        new Budget(YearMonth.of(2023,6), 300),
                        new Budget(YearMonth.of(2023,7), 310),
                        new Budget(YearMonth.of(2023,8), 3100))
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2023,5,2);
        LocalDate end = LocalDate.of(2023,8,2);
        assertEquals(3810, plan.query(start, end));
    }

    /**
     * 300+20
     */
    @Test
    public void budgetDaysIn2Year(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(new Budget(YearMonth.of(2023,11), 3000),
                        new Budget(YearMonth.of(2023,12), 310),
                        new Budget(YearMonth.of(2024,1), 310),
                        new Budget(YearMonth.of(2024,2), 3000)
                )
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2023,12,2);
        LocalDate end = LocalDate.of(2024,1,2);
        assertEquals(320, plan.query(start, end));
    }

    @Test
    public void budgetDaysIn3Year(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(
                Arrays.asList(
                        new Budget(YearMonth.of(2022,12), 3100),
                        new Budget(YearMonth.of(2023,1), 1000),
                        new Budget(YearMonth.of(2023,2), 1000),
                        new Budget(YearMonth.of(2023,3), 1000),
                        new Budget(YearMonth.of(2023,4), 1000),
                        new Budget(YearMonth.of(2023,5), 1000),
                        new Budget(YearMonth.of(2023,6), 1000),
                        new Budget(YearMonth.of(2023,7), 1000),
                        new Budget(YearMonth.of(2023,8), 1000),
                        new Budget(YearMonth.of(2023,9), 1000),
                        new Budget(YearMonth.of(2023,10), 1000),
                        new Budget(YearMonth.of(2023,11), 1000),
                        new Budget(YearMonth.of(2023,12), 1000),
                        new Budget(YearMonth.of(2024,1), 310),
                        new Budget(YearMonth.of(2024,2), 3000)
                )
        );
        BudgetPlan plan = new BudgetPlan(repo);
        LocalDate start = LocalDate.of(2022,12,2);
        LocalDate end = LocalDate.of(2024,1,2);
        assertEquals(15020, plan.query(start, end));
        //3000+12*1000+20
    }

}