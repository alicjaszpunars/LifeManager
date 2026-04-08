package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.DailyEntryDto;
import pl.coderslab.lifemanager.dto.HabitViewDailyDto;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor //konstruktor dla final
public class DailyViewService {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final MoodService moodService;
    private final HabitService habitService;
    private final DiaryService diaryService;


    //do agregacji widoku dziennego
    public DailyEntryDto getDailyView(LocalDate date){
        DailyEntryDto dto = new DailyEntryDto();
        dto.setDate(date);
        dto.setMood(moodService.getMood(date));
        dto.setDiary(diaryService.getDiary(date));
        dto.setHabits(habitService.dailyHabit(date).stream()
                .map(habitTracker -> new HabitViewDailyDto(
                        habitTracker.getHabit().getName(),
                        habitTracker.getDone()))
                .toList());
        dto.setExpenses(expenseService.dailyExpenseByCategory(date));
        dto.setIncomes(incomeService.dailyIncomeByCategory(date));
        return dto;

    }


}

