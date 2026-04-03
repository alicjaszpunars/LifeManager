package pl.coderslab.lifemanager.service;


//jeden status nawyku dla jednego dnia
//ale moze byc wiele nawykow kazdego dnia

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.HabitCreateDto;
import pl.coderslab.lifemanager.dto.HabitStatusDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Habit;
import pl.coderslab.lifemanager.entity.HabitTracker;
import pl.coderslab.lifemanager.repository.DailyEntryRepository;
import pl.coderslab.lifemanager.repository.HabitRepository;
import pl.coderslab.lifemanager.repository.HabitTrackerRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HabitService {

    private final DailyEntryRepository dailyEntryRepository;
    private final HabitTrackerRepository habitTrackerRepository;
    private final HabitRepository habitRepository;
    private final DailyEntryService dailyEntryService;


    //dodanie nowego nawyku
    public Habit createHabit(HabitCreateDto dto) {
        Habit habit = new Habit();
        habit.setName(dto.getName());
        habit.setActive(true);

        return habitRepository.save(habit);
    }

    //nie pokazywanie nawyku

    public void deactivateHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found " + habitId));
        habit.setActive(false);
        habitRepository.save(habit);
    }


    //ustawianie statusu dla dnia
    public HabitTracker setHabitTracker(HabitStatusDto dto) {

        //sprawdzenie czy istnieje nawyk
        Habit habit = habitRepository.findById(dto.getHabitId())
                .orElseThrow(() -> new IllegalArgumentException("Habit not found " + dto.getHabitId()));

        //sprawdzenie czy aktywny
        if (!Boolean.TRUE.equals((habit.getActive()))) { //dziala nawet dla pustej wartosci
            throw new IllegalArgumentException("Habit is not active");
        }

        //pobranie lub utworzenie
        DailyEntry day = dailyEntryService.getOrCreate(dto.getDate());

        //sprawdzenie czy istnieje juz ten nawyk tego dnia
        //optional bo moze nie istniec
        //jesli istnieje -> aktualizuje wartosc
        //jesli nie istnieje -> stworzenie wpisu dla tego dnia i tego nawyku
        Optional<HabitTracker> existing = habitTrackerRepository.findByHabitAndDailyEntry(habit, day);
        if (existing.isPresent()) {
            HabitTracker tracker = existing.get();
            tracker.setDone(dto.getDone());
            return habitTrackerRepository.save(tracker);
        }
        HabitTracker tracker = new HabitTracker();
        tracker.setHabit(habit);
        tracker.setDailyEntry(day);
        tracker.setDone(dto.getDone());

        return habitTrackerRepository.save(tracker);

    }


    //nawyki dla dnia

    public List<HabitTracker> dailyHabit(LocalDate date) {
        return dailyEntryService.findByDate(date)
                .map(habitTrackerRepository::findAllByDailyEntry)
                .orElse(Collections.emptyList());
    }

    //nawyk dla okresu

    public List<HabitTracker> periodHabit
            (Long habitId, LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found " + habitId));

        return habitTrackerRepository.findAllByHabitAndDailyEntry_DateBetween(habit, startDate, endDate);

    }
}
