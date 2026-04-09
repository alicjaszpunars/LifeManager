package pl.coderslab.lifemanager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.DailyEntryDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;

import java.time.LocalDate;
import java.util.Optional;


@Service

public class DailyEntryService {

    private final DailyEntryRepository dailyEntryRepository;

    public DailyEntryService(DailyEntryRepository dailyEntryRepository) {
        this.dailyEntryRepository = dailyEntryRepository;
    }


    //znajdowanie po dacie a jak nie ma to tworzenie nowego
    // -> zwraca optional bo w repo jest optional
    // find zwraca -> albo optional z obiektem albo pusty jesli nie ma
    // jak pusty -> tworzy nowy obiekt

    @Transactional //wykonuje te operacje razem
    public DailyEntry getOrCreate(LocalDate date){
        return dailyEntryRepository
                .findByDate(date)
                .orElseGet(()-> {DailyEntry entry = new DailyEntry();
                    entry.setDate(date);
                    return dailyEntryRepository.save(entry);});
    }

    //sprawdzenie czy wpis z takiego dnia istnieje zwroci pusty lub obiekt
    public Optional<DailyEntry> findByDate(LocalDate date){
        return dailyEntryRepository.findByDate(date);
    }


}

