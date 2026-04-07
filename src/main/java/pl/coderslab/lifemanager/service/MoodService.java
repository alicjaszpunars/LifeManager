package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Mood;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class MoodService {

    private final DailyEntryRepository dailyEntryRepository;
    private  final DailyEntryService dailyEntryService;


    //dodawanie mood do dnia
    public DailyEntry setMood(LocalDate date, Mood mood){
        DailyEntry entry= dailyEntryService.getOrCreate(date);
        entry.setMood(mood);
        return  dailyEntryRepository.save(entry);
    }

    //pobieranie mood dla dnia
    public Mood getMood(LocalDate date){
        return dailyEntryService.findByDate(date)
                .map(DailyEntry::getMood)
                .orElse(null);
    }

}
