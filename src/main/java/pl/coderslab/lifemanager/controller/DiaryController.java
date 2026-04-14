package pl.coderslab.lifemanager.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.service.DiaryService;

import java.time.LocalDate;
@Tag(name = "02. Diary", description = "Daily journal")
@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    @PostMapping("/{date}")
    public DailyEntry setDiary(@PathVariable LocalDate date, @RequestBody String diary) {
        return diaryService.setDiary(date, diary);
    }



    @GetMapping("/{date}")
    public String getDiary(@PathVariable LocalDate date) {
        return diaryService.getDiary(date);
    }
}
