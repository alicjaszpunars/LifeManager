package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.SavingValueCreateDto;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingValue;
import pl.coderslab.lifemanager.repository.SavingValueRepository;
import pl.coderslab.lifemanager.repository.SavingsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SavingValueService {

    private final SavingValueRepository savingValueRepository;
    private final SavingsRepository savingsRepository;


    //dodanie wartosci aktualnej
    public SavingValue addValue(Long savingId, SavingValueCreateDto dto) {
        Saving saving = savingsRepository.findById(savingId)
                .orElseThrow(() -> new IllegalArgumentException("Saving not found " + savingId));
        SavingValue value = new SavingValue();
        value.setSaving(saving);
        value.setValue(dto.getValue());
        value.setDate(dto.getDate());

        return savingValueRepository.save(value);
    }

    //aktualna wartosc (ostatni wpis)
    //optional bo mozne byc utworzony saving ale bez wartosci
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


//    public List<SavingValue> getValueAtDate(LocalDate date) {
//        return savingsRepository.findAll().stream() //zwraca wszystkie
//                .map(saving -> getValue(saving.getId(), date)) //zamienia Saving na optionala czy ma wartosc na ten dzien czy nie
//                .flatMap(Optional::stream) //
//                .filter(value -> value.getValue() > 0)
//                .toList();
//
//    }

}
