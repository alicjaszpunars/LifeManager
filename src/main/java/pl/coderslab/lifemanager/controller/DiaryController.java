package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.service.DiaryService;

import java.time.LocalDate;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;
    public DiaryController(DiaryService diaryService){
        this.diaryService = diaryService;
    }

    //tworzenie diary dla dnia
    @PostMapping("/{date}")
    public DailyEntry setDiary (@PathVariable LocalDate date, @RequestBody String diary){
        return diaryService.setDiary(date, diary);
    }


    //pobieranie diary z dnia
    @GetMapping ("/{date}")
    public String getDiary(@PathVariable LocalDate date){
        return diaryService.getDiary(date);
    }
}
