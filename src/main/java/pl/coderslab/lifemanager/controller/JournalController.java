package pl.coderslab.lifemanager.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.service.JournalService;

import java.time.LocalDate;

@RestController
@RequestMapping("/diary")
public class JournalController {

    private final JournalService journalService;
    public JournalController (JournalService journalService){
        this.journalService=journalService;
    }

    //tworzenie diary dla dnia
    @PostMapping("/{date}")
    public DailyEntry setDiary (@PathVariable LocalDate date, @RequestBody String diary){
        return journalService.setDiary(date, diary);
    }


    //pobieranie diary z dnia
    @GetMapping ("/{date}")
    public String getDiary(@PathVariable LocalDate date){
        return journalService.getDiary(date);
    }
}
