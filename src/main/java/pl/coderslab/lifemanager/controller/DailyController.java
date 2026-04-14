package pl.coderslab.lifemanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.lifemanager.dto.DailyEntryDto;
import pl.coderslab.lifemanager.service.DailyViewService;

import java.time.LocalDate;


@Tag(name = "01. Daily Entries", description = "Daily income, expense and mood")
@RestController
@RequestMapping("/day")
public class DailyController {

    private final DailyViewService dailyViewService;

    public DailyController(DailyViewService dailyViewService) {
        this.dailyViewService = dailyViewService;
    }


    @GetMapping("/{date}")
    public DailyEntryDto getDay(@PathVariable LocalDate date) {
        return dailyViewService.getDailyView(date);

    }

}
