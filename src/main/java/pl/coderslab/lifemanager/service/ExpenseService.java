package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.CategorySumDto;
import pl.coderslab.lifemanager.dto.ExpenseCreateDto;
import pl.coderslab.lifemanager.dto.IncomeCreateDto;
import pl.coderslab.lifemanager.entity.*;
import pl.coderslab.lifemanager.repository.ExpenseCategoryRepository;
import pl.coderslab.lifemanager.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ExpenseService {


    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final DailyEntryService dailyEntryService;


    //dodanie nowego wydatku
    public Expense createExpense(ExpenseCreateDto dto) {
        DailyEntry day = dailyEntryService.getOrCreate(dto.getDate());
        ExpenseCategory category = expenseCategoryRepository
                .findByCategory(dto.getCategoryName())
                .orElseThrow(() ->
                        new IllegalArgumentException("expense category not found " + dto.getCategoryName()));
        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setComment(dto.getComment());
        expense.setDailyEntry(day);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }


    //pobranie wydatków dla dnia
    public List<Expense> dailyExpense(LocalDate date) {
        Optional<DailyEntry> entry = dailyEntryService.findByDate(date);
        if (entry.isEmpty()) {
            return Collections.emptyList();
        }
        return expenseRepository.findAllByDailyEntry(entry.get());

    }

    //pobranie wydatków dla okresu
    //tu z zalozenia zbior moze byc pusty
    public List<Expense> periodExpense(LocalDate startDate, LocalDate endDate) {


        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        return expenseRepository.findAllByDailyEntry_Date_Between(startDate, endDate);

    }



//pobieranie dla kategorii
    public List<CategorySumDto> dailyExpenseByCategory (LocalDate date){
        Optional<DailyEntry> entry = dailyEntryService.findByDate(date);
        if (entry.isEmpty()){
            return Collections.emptyList();
        }
        return expenseRepository.ExpenseCategoryForDay(entry.get());
    }

    //edit
    public Expense updateExpense(Long id, ExpenseCreateDto dto) {
        Expense expense= expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found: " + id));

        ExpenseCategory expenseCategory = expenseCategoryRepository.findByCategory(dto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Expense category not found: " + dto.getCategoryName()));
        DailyEntry day = dailyEntryService.getOrCreate(dto.getDate());
        expense.setAmount(dto.getAmount());
        expense.setComment(dto.getComment());
        expense.setCategory(expenseCategory);
        expense.setDailyEntry(day);

        return expenseRepository.save(expense);

    }

    //delete
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new IllegalArgumentException("Income not found: " + id);
        }
        expenseRepository.deleteById(id);
    }

}
