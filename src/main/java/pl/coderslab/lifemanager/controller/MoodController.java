package pl.coderslab.lifemanager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Mood;
import pl.coderslab.lifemanager.service.MoodService;

import java.time.LocalDate;

@Tag(name = "04. Mood", description = "Daily mood")
@RestController
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping("/{date}")
    public DailyEntry setMood(@PathVariable LocalDate date, @RequestBody Mood mood) {
        return moodService.setMood(date, mood);
    }

    @GetMapping("/{date}")
    public Mood getMood(@PathVariable LocalDate date) {
        return moodService.getMood(date);
    }

}
