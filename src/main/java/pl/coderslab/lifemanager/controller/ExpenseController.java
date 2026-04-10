package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.ExpenseCreateDto;
import pl.coderslab.lifemanager.entity.Expense;
import pl.coderslab.lifemanager.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expense")


public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    //post dodanie wydatku

    @PostMapping
    public Expense add(@RequestBody ExpenseCreateDto dto) {
        return expenseService.createExpense(dto);

    }
    //get- wydatki z dnia

    @GetMapping("day/{date}")
    public List<Expense> getDailyEspenses(@PathVariable LocalDate date) {
        return expenseService.dailyExpense(date);
    }

    //get- wydatki z okresu

    @GetMapping("/period")
    public List<Expense> getPeriodExpanses(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return expenseService.periodExpense(start, end);
    }
}

