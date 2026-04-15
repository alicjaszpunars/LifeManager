package pl.coderslab.lifemanager.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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

    @Transactional
    public DailyEntry getOrCreate(LocalDate date) {
        return dailyEntryRepository
                .findByDate(date)
                .orElseGet(() -> {
                    DailyEntry entry = new DailyEntry();
                    entry.setDate(date);
                    return dailyEntryRepository.save(entry);
                });
    }

    public Optional<DailyEntry> findByDate(LocalDate date) {
        return dailyEntryRepository.findByDate(date);
    }


}

