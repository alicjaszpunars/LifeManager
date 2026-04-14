package pl.coderslab.lifemanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.SummaryDto;
import pl.coderslab.lifemanager.service.SummaryService;

import java.time.LocalDate;
@Tag(name = "10. Summary ", description = "Summaries for a user-defined date range including income, expenses, habits and current savings value")
@RestController
@RequestMapping("/summary")
public class SummaryPeriodController {

    private final SummaryService summaryService;

    public SummaryPeriodController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("period")
    public SummaryDto getSummary(LocalDate startDate, LocalDate endDate) {
        SummaryDto dto = new SummaryDto();
        dto.setHabits(summaryService.getHabitSummary(startDate, endDate));
        dto.setIncomes(summaryService.getIncomeSummary(startDate, endDate));
        dto.setExpenses(summaryService.getExpenseSummary(startDate, endDate));
        dto.setSavings(summaryService.getSavingSummary(startDate, endDate));

        return dto;


    }
}


