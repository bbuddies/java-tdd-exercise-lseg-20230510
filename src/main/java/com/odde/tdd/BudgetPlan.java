package com.odde.tdd;

import java.time.*;
import java.util.List;

public class BudgetPlan {
    private final BudgetRepo repo;

    public BudgetPlan(BudgetRepo repo) {
        this.repo = repo;
    }

    public double query(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.findAll();
        double result = 0;
        int startYear = start.getYear();
        int endYear = end.getYear();
        int startMonth = start.getMonth().getValue();
        int endMonth = end.getMonth().getValue();

        if(startYear == endYear){

            if(startMonth == endMonth){
                double durationDays = end.toEpochDay() - start.toEpochDay() + 1;//Duration.between(start, end).toDays();
                double lengthOfMonth = start.lengthOfMonth();
                result += getAmount(budgets, YearMonth.of(start.getYear(), start.getMonth()))*durationDays/lengthOfMonth;
                return result;
            }else{

                for(int i = startMonth; i<= endMonth;i++){
                    if(i == startMonth){
                        long lengthOfMonth = start.lengthOfMonth();
                        result += getAmount(budgets, YearMonth.of(startYear, start.getMonth())) * (lengthOfMonth - start.getDayOfMonth() +1) / lengthOfMonth;
                    }else if(i == endMonth){
                        result += getAmount(budgets, YearMonth.of(endYear, end.getMonth())) * end.getDayOfMonth() / end.lengthOfMonth();
                    }else{
                        result += getAmount(budgets, YearMonth.of(endYear, Month.of(i)));
                    }
                }
            }
        }else{

            for(int i = startYear; i<= endYear; i++){
                if(i==startYear){
                    result += query(start, LocalDate.of(startYear,12,31));
                }else if(i == endYear){
                    result += query(LocalDate.of(endYear,1,1), end);
                }else{
                    result += query(LocalDate.of(i,1,1), LocalDate.of(i,12,31));
                }
            }

        }

        return result;
    }

    private long getAmount(List<Budget> list, YearMonth date){
        for(Budget bd: list){
            if(bd.getMonth().equals(date)){
                return bd.getAmount();
            }
        }
        return 0;
    }
}
