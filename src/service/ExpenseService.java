package service;

import model.Category;
import model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ArrayList<Expense> expenses = new ArrayList<>();


    public void addExpense(Expense expense){
        this.expenses.add(expense);
    }

    public void deleteExpense(UUID id){
        Iterator<Expense> expenseIterator = this.expenses.iterator();
        while (expenseIterator.hasNext()){
            if (expenseIterator.next().getId().equals(id)){
                expenseIterator.remove();
                return;
            }
        }
    }

    public List<Expense> filterByCategory(Category category){
        return this.expenses.stream()
                .filter(expense -> expense.getCategory() == category)
                .collect(Collectors.toList());
    }

    public double getTotalExpense(){
        return this.expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public List<Expense> filterByDate(LocalDate start, LocalDate end){
        return this.expenses.stream()
                .filter(expense -> expense.getDate().isAfter(start) && expense.getDate().isBefore(end))
                .toList();
    }

    public List<Expense> showExpenses() {
        return expenses;
    }



}
