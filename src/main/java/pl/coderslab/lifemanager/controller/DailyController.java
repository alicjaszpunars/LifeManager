package pl.coderslab.lifemanager.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.lifemanager.service.DailyEntryService;


//kontroler ma odbieraz dane, wywoluje serwis, zwraca wynik -> BEZ LOGIKI
//ma wstrzykniety service- kontroler komunikuje sie z serwisem, nie z repo
@RestController
@RequestMapping ("/lifeManager/day")
public class DailyController {

    private final DailyEntryService dailyEntryService;

    public DailyController(DailyEntryService dailyEntryService) {
        this.dailyEntryService = dailyEntryService;
    }


    //tylko get zwraca wszystko dotyczace jednego dnia
    //zwraca dni ktore maja wpisy
}
