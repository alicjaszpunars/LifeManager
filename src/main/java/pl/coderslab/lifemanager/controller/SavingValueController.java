package pl.coderslab.lifemanager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.SavingValueCreateDto;
import pl.coderslab.lifemanager.entity.SavingValue;
import pl.coderslab.lifemanager.service.SavingValueService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "08. Saving value", description = "Value at date")
@RestController
@RequestMapping("saving/value")
public class SavingValueController {
    private final SavingValueService savingValueService;

    public SavingValueController(SavingValueService savingValueService) {
        this.savingValueService = savingValueService;
    }

    @PostMapping("/{savingId}")
    public SavingValue addValue(@PathVariable Long savingId, @RequestBody SavingValueCreateDto dto) {
        return savingValueService.addValue(savingId, dto);
    }

    @GetMapping("/{savingId}/actual")
    public Optional<SavingValue> getActual(@PathVariable Long savingId) {
        return savingValueService.getLatestValue(savingId);
    }

    @GetMapping("/{savingId}/first")
    public Optional<SavingValue> getFirstValue(@PathVariable Long savingId) {
        return savingValueService.getFirstValue(savingId);
    }

    @GetMapping("/{date}")
    public List<SavingValue> getValueForDate(@PathVariable LocalDate date) {
        return savingValueService.getValueAtDate(date);
    }

    @GetMapping("/last_update")
    public Map<Long, LocalDate> getLastUpdate() {
        return savingValueService.getLastUpdateDates();
    }
}
