package pl.coderslab.lifemanager.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.*;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.repository.SavingsRepository;
import pl.coderslab.lifemanager.service.SavingService;

import java.util.List;

@RestController
@RequestMapping ("saving")
public class SavingController {

    private final SavingService savingService;


    public SavingController (SavingService savingService, SavingsRepository savingsRepository){
        this.savingService=savingService;
    }


    //tworzenie w podziale na rodzaje
    // STOCK
    @PostMapping("/stock")
    public Saving createStock(@RequestBody SavingStockCreateDto dto){
        return savingService.createSavingStock(dto);
    }

    //CURRENCY
    @PostMapping("/currency")
    public Saving createCurrency(@RequestBody SavingCurrencyCreateDto dto){
        return  savingService.createSavingCurrency(dto);
    }

    //BONDS
    @PostMapping("/bond")
    public Saving createBond(@RequestBody SavingBondsCreateDto dto){
        return  savingService.createSavingBond(dto);
    }

    //PPK
    @PostMapping("/ppk")
    public Saving createPpk(@RequestBody SavingsPPKCreateDto dto){
        return  savingService.createSavingPPK(dto);
    }

    //DEPOSITS
    @PostMapping("/deposit")
    public Saving createDeposit(@RequestBody SavingDepositsCreateDto dto){
        return savingService.createSavingDeposit(dto);
    }

    //get pokazuje wszystkie
    @GetMapping
    public List<Saving> getAll (){
        return savingService.getAllSavings();
    }
}
