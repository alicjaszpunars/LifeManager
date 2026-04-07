package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.IncomeCreateDto;
import pl.coderslab.lifemanager.entity.Income;
import pl.coderslab.lifemanager.service.IncomeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;
    public  IncomeController (IncomeService incomeService){
        this.incomeService=incomeService;
    }

    //post - dodawanie nowego

    @PostMapping
    public Income add (@RequestBody IncomeCreateDto dto){
        return  incomeService.createIncome(dto);
    }

    //get wydatki z dnia
    @GetMapping ("day/{date}")
    public List<Income> getDailyIncomes(@PathVariable LocalDate date) {
        return incomeService.dailyIncome(date);
    };

    //get wydatki z okresu
    @GetMapping("/period")
    public List<Income> getPeriodincomes(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return  incomeService.periodIncome(start, end);
    }
}
