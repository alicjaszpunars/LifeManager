package pl.coderslab.lifemanager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.DailyEntryDto;
import pl.coderslab.lifemanager.dto.HabitViewDailyDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor //konstruktor dla final
public class DailyViewService {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final MoodService moodService;
    private final HabitService habitService;
    private final JournalService journalService;


    //do agregacji widoku dziennego
    public DailyEntryDto getDailyView(LocalDate date){
        DailyEntryDto dto = new DailyEntryDto();
        dto.setDate(date);
        dto.setMood(moodService.getMood(date));
        dto.setDiary(journalService.getDiary(date));
        dto.setHabits(habitService.dailyHabit(date).stream()
                .map(habitTracker -> new HabitViewDailyDto(
                        habitTracker.getHabit().getId(),
                        habitTracker.getHabit().getName(),
                        habitTracker.getDone()))
                .toList());
        dto.setExpenses(expenseService.dailyExpenseByCategory(date));
        dto.setIncomes(incomeService.dailyIncomeByCategory(date));
        return dto;

    }


}

