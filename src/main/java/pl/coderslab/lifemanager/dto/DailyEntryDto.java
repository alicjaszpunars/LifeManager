package pl.coderslab.lifemanager.dto;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.lifemanager.entity.Expense;
import pl.coderslab.lifemanager.entity.HabitTracker;
import pl.coderslab.lifemanager.entity.Income;
import pl.coderslab.lifemanager.entity.Mood;

import java.time.LocalDate;
import java.util.List;

//widok dzienny bedzie tylko agregowal dane dla 1 dnia -> wszystko w 1 miejscu, read only
@Getter
@Setter
public class DailyEntryDto {
    private LocalDate date;
    private Mood mood;
    private String diary;
    private List<CategorySumDto> expenses;
    private List<CategorySumDto> incomes;
    private List<HabitViewDailyDto> habits;
}
