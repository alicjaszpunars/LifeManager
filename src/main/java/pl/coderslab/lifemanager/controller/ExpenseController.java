package pl.coderslab.lifemanager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.ExpenseCreateDto;
import pl.coderslab.lifemanager.entity.Expense;
import pl.coderslab.lifemanager.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "06. Expense", description = "Daily expense")
@RestController
@RequestMapping("/expense")


public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense add(@RequestBody ExpenseCreateDto dto) {
        return expenseService.createExpense(dto);

    }

    @GetMapping("day/{date}")
    public List<Expense> getDailyEspenses(@PathVariable LocalDate date) {
        return expenseService.dailyExpense(date);
    }

    @GetMapping("/period")
    public List<Expense> getPeriodExpanses(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return expenseService.periodExpense(start, end);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id, @RequestBody ExpenseCreateDto dto) {
        return expenseService.updateExpense(id,dto);
    }
}

