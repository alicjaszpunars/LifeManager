package pl.coderslab.lifemanager.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DailyEntryRepository dailyEntryRepository;
    private final  DailyEntryService dailyEntryService;

    //dodawanie wpisu do dnia
    public DailyEntry setDiary (LocalDate date, String diary){
        DailyEntry entry= dailyEntryService.getOrCreate(date);
        entry.setDiary(diary);
        return  dailyEntryRepository.save(entry);
    }

    //pobieranie wpisu dla dnia
    public String getDiary (LocalDate date){
        return  dailyEntryService.findByDate(date)
                .map(DailyEntry::getDiary)
                .orElse(null);
    }
}
