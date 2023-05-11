package com.odde.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetServiceTest {

    BudgetRepo budgetRepo = mock(BudgetRepo.class);
    BudgetService budgetService = new BudgetService(budgetRepo);

    @BeforeEach
    void setUp() {
        when(budgetRepo.findAll()).thenReturn(mockBudget());
    }

    @Test
    public void start_date_is_after_end_date(){
        Assertions.assertEquals(0,budgetService.budget(new Period(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 9))));
    }

    @Test
    public void out_of_existing_budgets(){
        Assertions.assertEquals(0,budgetService.budget(new Period(LocalDate.of(2023, 3, 10), LocalDate.of(2023, 3, 12))));
    }

    @Test
    public void within_same_month(){
        Assertions.assertEquals(100*14,budgetService.budget(new Period(LocalDate.of(2023, 5, 2), LocalDate.of(2023, 5, 15))));
    }

    @Test
    public void one_day(){
        Assertions.assertEquals(100,budgetService.budget(new Period(LocalDate.of(2023, 5, 2), LocalDate.of(2023, 5, 2))));
    }

    @Test
    public void across_multiple_months(){
        Assertions.assertEquals(100*30 + 300 + 310 + 100*15,budgetService.budget(new Period(LocalDate.of(2023, 5, 2), LocalDate.of(2023, 8, 15))));
    }

    @Test
    public void start_before_budget(){
        Assertions.assertEquals(100 * 15,budgetService.budget(new Period(LocalDate.of(2023, 4, 2), LocalDate.of(2023, 5, 15))));
    }


    @Test
    public void end_after_budget(){
        Assertions.assertEquals(100*30,budgetService.budget(new Period(LocalDate.of(2023, 8, 2), LocalDate.of(2023, 9, 15))));
    }

    @Test
    void out_of_order_budgets() {
        List<Budget> list = new ArrayList<>();
        list.add(new Budget(YearMonth.of(2023,7), 310));
        list.add(new Budget(YearMonth.of(2023,5), 3100));
        list.add(new Budget(YearMonth.of(2023,8), 3100));
        list.add(new Budget(YearMonth.of(2023,6), 300));
        when(budgetRepo.findAll()).thenReturn(list);
        Assertions.assertEquals(10*12 + 100*13,budgetService.budget(new Period(LocalDate.of(2023, 7, 20), LocalDate.of(2023, 8, 13))));
    }

    @Test
    void missing_budget() {
        List<Budget> list = new ArrayList<>();
        list.add(new Budget(YearMonth.of(2023,5), 3100));
        list.add(new Budget(YearMonth.of(2023,8), 3100));
        when(budgetRepo.findAll()).thenReturn(list);
        Assertions.assertEquals(100*2 + 100*3,budgetService.budget(new Period(LocalDate.of(2023, 5, 30), LocalDate.of(2023, 8, 3))));
    }

    @Test
    void no_budget() {
        when(budgetRepo.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,budgetService.budget(new Period(LocalDate.of(2023, 5, 30), LocalDate.of(2023, 8, 3))));
    }

    private List<Budget> mockBudget() {
        List<Budget> list = new ArrayList<>();
        list.add(new Budget(YearMonth.of(2023,5), 3100));
        list.add(new Budget(YearMonth.of(2023,6), 300));
        list.add(new Budget(YearMonth.of(2023,7), 310));
        list.add(new Budget(YearMonth.of(2023,8), 3100));
        return list;
    }

}