package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.SavingValueCreateDto;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingValue;
import pl.coderslab.lifemanager.repository.SavingValueRepository;
import pl.coderslab.lifemanager.repository.SavingsRepository;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SavingValueService {

    private final SavingValueRepository savingValueRepository;
    private final SavingsRepository savingsRepository;


    //dodanie wartosci aktualnej manualnie
    public SavingValue addValue(Long savingId, SavingValueCreateDto dto) {
        if (dto.getDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date cannot be in the future");
        }

        Saving saving = savingsRepository.findById(savingId)
                .orElseThrow(() -> new IllegalArgumentException("Saving not found " + savingId));
        SavingValue value = new SavingValue();
        value.setSaving(saving);
        value.setValue(dto.getValue());
        value.setDate(dto.getDate());

        return savingValueRepository.save(value);
    }

    // zapisanie wartosci sciagnietej
    public SavingValue saveMarketValue(Saving saving, LocalDate date, double value){
        SavingValue savingValue= new SavingValue();
        savingValue.setSaving(saving);
        savingValue.setDate(date);
        savingValue.setValue(value);
        return savingValueRepository.save(savingValue);
    }

    //aktualna wartosc (ostatni wpis)
    public Optional<SavingValue> getLatestValue(Long savingId) {
        Saving saving = savingsRepository.findById(savingId)
                .orElseThrow(() -> new IllegalArgumentException("Saving not found " + savingId));
        return savingValueRepository.findFirstBySavingOrderByDateDesc(saving);
    }

    //pierwszy wpis

    public Optional<SavingValue> getFirstValue(Long savingId) {
        Saving saving = savingsRepository.findById(savingId)
                .orElseThrow(() -> new IllegalArgumentException("Saving not found " + savingId));
        return savingValueRepository.findFirstBySavingOrderByDateAsc(saving);

    }


    //wszystkie wartosci na dzien (lub najblizsza data)
    //tylko dla niezerwoych wartosci

    public List<SavingValue> getValueAtDate(LocalDate date) {
        List<SavingValue> result = new ArrayList<>();

        for (Saving saving : savingsRepository.findAll()) {

            Optional<SavingValue> optional =
            savingValueRepository.findTopBySavingAndDateLessThanEqualOrderByDateDesc(saving, date);

            if (optional.isPresent()) {
                SavingValue value = optional.get();

                if (value.getValue() > 0) {
                    result.add(value);
                }
            }
        }
        return result;


    }

    //sprawdzenie czy jest wpis z dnia
    public boolean existForDate (Saving saving, LocalDate date ){
        return savingValueRepository.findBySavingAndDate(saving,date).isPresent();
    }

    //daty ostatniej aktualizacji

    public Map<Long, LocalDate> getLastUpdateDates() {
        Map<Long, LocalDate> result = new HashMap<>();

        for (Saving saving : savingsRepository.findAll()) {
            savingValueRepository.findFirstBySavingOrderByDateDesc(saving)
                    .ifPresent(v -> result.put(saving.getId(), v.getDate()));
        }
        return result;
    }



}
