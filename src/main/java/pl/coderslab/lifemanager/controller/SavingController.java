package pl.coderslab.lifemanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.*;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.repository.SavingsRepository;
import pl.coderslab.lifemanager.service.SavingService;

import java.util.List;
@Tag(name = "07. Saving", description = "Saving")
@RestController
@RequestMapping("saving")
public class SavingController {

    private final SavingService savingService;


    public SavingController(SavingService savingService, SavingsRepository savingsRepository) {
        this.savingService = savingService;
    }

    @PostMapping("/stock")
    public Saving createStock(@RequestBody SavingStockCreateDto dto) {
        return savingService.createSavingStock(dto);
    }

    @PostMapping("/currency")
    public Saving createCurrency(@RequestBody SavingCurrencyCreateDto dto) {
        return savingService.createSavingCurrency(dto);
    }

    @PostMapping("/bond")
    public Saving createBond(@RequestBody SavingBondsCreateDto dto) {
        return savingService.createSavingBond(dto);
    }

    @PostMapping("/ppk")
    public Saving createPpk(@RequestBody SavingsPPKCreateDto dto) {
        return savingService.createSavingPPK(dto);
    }

    @PostMapping("/deposit")
    public Saving createDeposit(@RequestBody SavingDepositsCreateDto dto) {
        return savingService.createSavingDeposit(dto);
    }

    @GetMapping
    public List<Saving> getAll() {
        return savingService.getAllSavings();
    }
}
