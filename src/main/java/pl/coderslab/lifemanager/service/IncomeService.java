package pl.coderslab.lifemanager.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.CategorySumDto;
import pl.coderslab.lifemanager.dto.IncomeCreateDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Income;
import pl.coderslab.lifemanager.entity.IncomeCategory;
import pl.coderslab.lifemanager.repository.IncomeCategoryRepository;
import pl.coderslab.lifemanager.repository.IncomeRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;
    private final DailyEntryService dailyEntryService;

    public Income createIncome(IncomeCreateDto dto) {
        DailyEntry day = dailyEntryService.getOrCreate(dto.getDate());
        IncomeCategory category = incomeCategoryRepository
                .findByCategory(dto.getCategoryName())
                .orElseThrow(() ->
                        new IllegalArgumentException("income category not found "
                                + dto.getCategoryName()));
        Income income = new Income();
        income.setAmount(dto.getAmount());
        income.setComment(dto.getComment());
        income.setDailyEntry(day);
        income.setIncomeCategory(category);

        return incomeRepository.save(income);
    }


    //income z dnia
    public List<Income> dailyIncome(LocalDate date) {
        Optional<DailyEntry> entry = dailyEntryService.findByDate(date);
        if (entry.isEmpty()) {
            return Collections.emptyList();
        }
        return incomeRepository.findAllByDailyEntry(entry.get());
    }

    //income z okresu
    public List<Income> periodIncome(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        return incomeRepository.findAllByDailyEntry_Date_Between(startDate, endDate);
    }

    //income po kategoriach
    public List<CategorySumDto> dailyIncomeByCategory(LocalDate date) {
        Optional<DailyEntry> entry = dailyEntryService.findByDate(date);
        if (entry.isEmpty()) {
            return Collections.emptyList();
        }
        return incomeRepository.IncomeCategoryForDay(entry.get());
    }

    //edit
    public Income updateIncome(Long id, IncomeCreateDto dto) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Income not found: " + id));

        IncomeCategory incomeCategory = incomeCategoryRepository.findByCategory(dto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Income category not found: " + dto.getCategoryName()));
        DailyEntry day = dailyEntryService.getOrCreate(dto.getDate());
        income.setAmount(dto.getAmount());
        income.setComment(dto.getComment());
        income.setIncomeCategory(incomeCategory);
        income.setDailyEntry(day);

        return incomeRepository.save(income);

    }

    //delete
    public void deleteIncome(Long id) {
        if (!incomeRepository.existsById(id)) {
            throw new IllegalArgumentException("Income not found: " + id);
        }
        incomeRepository.deleteById(id);
    }
}


