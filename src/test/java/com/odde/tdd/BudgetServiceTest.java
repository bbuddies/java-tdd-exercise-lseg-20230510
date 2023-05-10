package com.odde.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetServiceTest {


    @Test
    public void BeforeNotExpectedCase(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(0,budgetService.budget(LocalDate.of(2023,5,10), LocalDate.of(2023,5,9)));
    }

    @Test
    public void BeforeMay1st(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(0,budgetService.budget(LocalDate.of(2023,3,10), LocalDate.of(2023,3,12)));
    }

    @Test
    public void BeforeMay2ndToMay15Th(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(1400,budgetService.budget(LocalDate.of(2023,5,2), LocalDate.of(2023,5,15)));
    }

    @Test
    public void BeforeMay2ndToMay2nd(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(100,budgetService.budget(LocalDate.of(2023,5,2), LocalDate.of(2023,5,2)));
    }

    @Test
    public void BeforeMay2ndToAug15Th(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(5110,budgetService.budget(LocalDate.of(2023,5,2), LocalDate.of(2023,8,15)));
    }

    @Test
    public void BeforeMar2ndToAug15Th(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(5210,budgetService.budget(LocalDate.of(2023,2,2), LocalDate.of(2023,8,15)));
    }


    @Test
    public void BeforeMay2ndToDec15Th(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(6710,budgetService.budget(LocalDate.of(2023,5,2), LocalDate.of(2023,12,15)));
    }

    @Test
    public void BeforeDec2ndToDec15Th(){
        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(mockBudget());
        BudgetService budgetService = new BudgetService(budgetRepo);
        Assertions.assertEquals(0,budgetService.budget(LocalDate.of(2023,12,2), LocalDate.of(2023,12,15)));
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