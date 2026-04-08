package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.CategorySumDto;
import pl.coderslab.lifemanager.dto.HabitViewSummaryDto;
import pl.coderslab.lifemanager.dto.SavingSummaryDto;
import pl.coderslab.lifemanager.entity.*;
import pl.coderslab.lifemanager.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final SavingsRepository savingsRepository;
    private final SavingValueRepository savingValueRepository;
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final HabitTrackerRepository habitTrackerRepository;
    private final HabitRepository habitRepository;


    //----------------------------------wydatki i przychody

    public List<CategorySumDto> getExpenseSummary(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.sumExpenseCategoryForPeriod(startDate, endDate);
    }

    public List<CategorySumDto> getIncomeSummary(LocalDate startDate, LocalDate endDate) {
        return incomeRepository.sumIncomeCategoryForPeriod(startDate, endDate);
    }

    //----------------------------------nawyki

    public List<HabitViewSummaryDto> getHabitSummary(LocalDate startDate, LocalDate endDate) {
        List<Habit> habits = habitRepository.findAllByActiveTrue(); //znajdujemy wszystkie aktywne
        List<HabitViewSummaryDto> results = new ArrayList<>();

        for (Habit habit : habits) {
            List<HabitTracker> trackers = habitTrackerRepository.findAllByHabitAndDailyEntry_DateBetween(habit, startDate, endDate);
            int daysDone = 0;
            for (HabitTracker tracker : trackers) {
                if (Boolean.TRUE.equals(tracker.getDone())) {
                    daysDone++;
                }
            }
            results.add(new HabitViewSummaryDto(habit.getName(), daysDone));
        }
        return results;
    }


    //----------------------------------oszczednosci
    // -> robimy globalna sume do liczenia procentow
    //rozrozniamy na kategorie, przechodzimy petla po kazdym saving dopasowujemy do kategorii jak istnieje i powiekszamy jej sume
    //jak to pierwszy z rodzaju to tworzymy nowa kategorie i suma 0

    public List<SavingSummaryDto> getSavingSummary(LocalDate startDate, LocalDate endDate) {
        List<Saving> savings = savingsRepository.findAll(); //pobieram wszystkie


        Map<String, SavingSummaryDto> summaryByCategory = new HashMap<>();
        //tworze mape do przypisania klucz np bonds: summarydto - wartosci start, end, change, %
        double totalEndAmount = 0;

        //pierwsza petla idzie po kazdym saving i wpisuje wartosc poczatkowa i koncowa

        for (Saving saving : savings) {
            String type = saving.getSavingType(); //przypisuje typ
            double startValue = savingValueRepository.findTopBySavingAndDateLessThanEqualOrderByDateDesc(saving, startDate)
                    .map(SavingValue::getValue)
                    .orElse(0.0);
            double endValue = savingValueRepository.findTopBySavingAndDateLessThanEqualOrderByDateDesc(saving, endDate)
                    .map(SavingValue::getValue)
                    .orElse(0.00);

            SavingSummaryDto dto = summaryByCategory.get(type);
            //jak jeszcze nie ma to tworzymy nowe dto dla kategorii
            if (dto == null) {
                dto = new SavingSummaryDto();
                dto.setSavingType(type);
                dto.setStartAmount(0.0);
                dto.setEndAmount(0.0);
                summaryByCategory.put(type, dto);
            }

            dto.setStartAmount(dto.getStartAmount() + startValue);
            dto.setEndAmount(dto.getEndAmount() + endValue);
            totalEndAmount += endValue;


        }

        //druga petla jak ma juz wartosci dla kazdej kategorii to wpisuje zmiane i %
        for (SavingSummaryDto dto : summaryByCategory.values()) {
            dto.setChangeAmount(dto.getEndAmount() - dto.getStartAmount());
            double percentage = Math.round((dto.getEndAmount() / totalEndAmount * 100) * 100.0) / 100.0;
            dto.setPercentage(percentage);

        }

        //nie pokazujemy jesli cos mialo 0 na start i end
        List<SavingSummaryDto> result= new ArrayList<>();
        for (SavingSummaryDto dto : summaryByCategory.values()){
            if (dto.getStartAmount() != 0.0 || dto.getEndAmount() !=0.0)
                result.add(dto);
        }


        return result;
    }
}



