package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.SavingValueCreateDto;
import pl.coderslab.lifemanager.entity.SavingValue;
import pl.coderslab.lifemanager.service.SavingService;
import pl.coderslab.lifemanager.service.SavingValueService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("saving/value")
public class SavingValueController {
    private final SavingValueService savingValueService;

   public SavingValueController (SavingValueService savingValueService){
       this.savingValueService= savingValueService;
    }

    //post - dodawanie wartosci aktualnej dla konkretnego
    @PostMapping("/{savingId}")
    public SavingValue addValue(@PathVariable Long savingId, @RequestBody SavingValueCreateDto dto){
       return savingValueService.addValue(savingId, dto);
    }


    //get- wartosc aktualna,
    @GetMapping("/{savingId}/actual")
    public Optional<SavingValue> getActual(@PathVariable Long savingId){
       return savingValueService.getLatestValue(savingId);
    }

    //wartosc pierwsza,
    @GetMapping("/{savingId}/first")
    public Optional<SavingValue> getFirstValue(@PathVariable Long savingId){
       return  savingValueService.getFirstValue(savingId);
    }


    //wartosci na dzien
    @GetMapping("/{date}")
    public List<SavingValue> getValueForDate(@PathVariable LocalDate date){
       return savingValueService.getValueAtDate(date);
    }
}
