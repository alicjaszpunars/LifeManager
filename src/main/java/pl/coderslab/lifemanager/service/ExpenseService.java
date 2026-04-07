package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.ExpenseCreateDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Expense;
import pl.coderslab.lifemanager.entity.ExpenseCategory;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;
import pl.coderslab.lifemanager.repository.ExpenseCategoryRepository;
import pl.coderslab.lifemanager.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


//Użytkownik dodaje / edytuje / przegląda wydatek przypisany do konkretnego dnia
// musi powiązać wydatek z dniem i kategorią oraz go zapisac

@RequiredArgsConstructor
@Service
public class ExpenseService {


    private final DailyEntryRepository dailyEntryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final DailyEntryService dailyEntryService;

    //to robi @RequiredArgsConstructor
//    public ExpenseService(DailyEntryRepository dailyEntryRepository,
//                          ExpenseRepository expenseRepository,
//                          ExpenseCategoryRepository expenseCategoryRepository, DailyEntryService dailyEntryService) {
//        this.dailyEntryRepository = dailyEntryRepository;
//        this.expenseRepository = expenseRepository;
//        this.expenseCategoryRepository = expenseCategoryRepository;
//        this.dailyEntryService = dailyEntryService;
//    }

    //dodanie nowego wydatku
    //wydatek MUSI miec date (pobieramy z daily) i kategorie (pobieramy), jak nie ma -> wyjatek
    //utworzenie nowego
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

    //edycja wydatku
    // public Expense editExpanse

    //pobranie wydatków dla dnia
    //najpierw musimy sprawdzic czy jest jakis wpis -> jak nie ma to wyrzuci pusta liste
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

}
