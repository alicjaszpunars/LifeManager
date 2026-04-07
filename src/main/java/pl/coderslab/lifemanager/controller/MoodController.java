package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Mood;
import pl.coderslab.lifemanager.service.MoodService;

import java.time.LocalDate;

@RestController
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;
    public MoodController(MoodService moodService){
        this.moodService=moodService;
    }

    //tworzenie mood dla dnia
    @PostMapping ("/{date}")
    public DailyEntry setMood (@PathVariable LocalDate date, @RequestBody Mood mood){
        return moodService.setMood(date, mood);
    }

    //zwracanie mood dla dnia
    @GetMapping ("/{date}")
    public  Mood getMood (@PathVariable LocalDate date){
        return  moodService.getMood(date);
    }

}
