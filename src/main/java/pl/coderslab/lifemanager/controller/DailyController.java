package pl.coderslab.lifemanager.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.lifemanager.dto.DailyEntryDto;
import pl.coderslab.lifemanager.service.DailyEntryService;
import pl.coderslab.lifemanager.service.DailyViewService;

import java.time.LocalDate;


//kontroler ma odbierac dane, wywoluje serwis, zwraca wynik -> BEZ LOGIKI
//ma wstrzykniety service- kontroler komunikuje sie z serwisem, nie z repo
@RestController
@RequestMapping ("/day")
public class DailyController {

    private final DailyViewService dailyViewService;

    public DailyController(DailyViewService dailyViewService) {
        this.dailyViewService = dailyViewService;
    }

    //tylko get zwraca wszystko dotyczace jednego dnia
    @GetMapping("/{date}")
    public DailyEntryDto getDay(@PathVariable LocalDate date){
        return dailyViewService.getDailyView(date);

    }

}
