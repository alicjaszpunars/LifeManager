package pl.coderslab.lifemanager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.IncomeCreateDto;
import pl.coderslab.lifemanager.entity.Income;
import pl.coderslab.lifemanager.service.IncomeService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "05. Income", description = "Daily income")
@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public Income add(@RequestBody IncomeCreateDto dto) {
        return incomeService.createIncome(dto);
    }

    @GetMapping("day/{date}")
    public List<Income> getDailyIncomes(@PathVariable LocalDate date) {
        return incomeService.dailyIncome(date);
    }

    @GetMapping("/period")
    public List<Income> getPeriodincomes(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return incomeService.periodIncome(start, end);
    }
}
