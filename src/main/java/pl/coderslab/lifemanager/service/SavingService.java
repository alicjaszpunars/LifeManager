package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.dto.*;
import pl.coderslab.lifemanager.entity.*;
import pl.coderslab.lifemanager.repository.SavingsRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
//zarzadza definicja oszczednosci -> jakie rodzaje, ale nie interesuje tutaj nas wartosc ani poczatkowa ani aktualna
public class SavingService {

    private final SavingsRepository savingsRepository;

    //tworzenie nowych
    //stock
    public Saving createSavingStock (SavingStockCreateDto dto){
        SavingStock saving = new SavingStock();
        saving.setName(dto.getName());
        saving.setTicker(dto.getTicker());
        saving.setQuantity(dto.getQuantity());
        saving.setCurrencyCode(dto.getCurrencyCode());
        saving.setComment(dto.getComment());

        //ustaw date -> jesli podana to wpisz, jak nie to wpisz dzisiejsza
        //-> mozna to wyniesc wyzej dla wszystkich jako metoda bo sie powtarza
        saving.setStartDate(dto.getStartDate() !=null ? dto.getStartDate() : LocalDate.now());
        return  savingsRepository.save(saving);
    }

    //bonds
    public Saving createSavingBond (SavingBondsCreateDto dto){
        SavingBond saving = new SavingBond();
        saving.setName(dto.getName());
        saving.setQuantity( dto.getQuantity());
        saving.setDurationYears(dto.getDurationYears());
        saving.setComment(dto.getComment());

        saving.setStartDate(dto.getStartDate() !=null ? dto.getStartDate() : LocalDate.now());
        return  savingsRepository.save(saving);

    }

    //currency
    public Saving createSavingCurrency (SavingCurrencyCreateDto dto){
        SavingCurrency savingCurrency = new SavingCurrency();
        savingCurrency.setCurrencyCode(dto.getCurrencyCode());
        savingCurrency.setName(dto.getCurrencyCode()); //musi byc nazwa bo w saving jest nazwa
        savingCurrency.setQuantity(dto.getQuantity());
        savingCurrency.setComment(dto.getComment());

        savingCurrency.setStartDate(dto.getStartDate() !=null ? dto.getStartDate() : LocalDate.now());
        return  savingsRepository.save(savingCurrency);

    }

    //deposits
    public Saving createSavingDeposit (SavingDepositsCreateDto dto){
        SavingDeposit savingDeposit = new SavingDeposit();
        savingDeposit.setName(dto.getName());
        savingDeposit.setAmount(dto.getAmount());
        savingDeposit.setComment(dto.getComment());

        savingDeposit.setStartDate(dto.getStartDate() !=null ? dto.getStartDate() : LocalDate.now());
        return  savingsRepository.save(savingDeposit);
    }

    //ppk

    public Saving createSavingPPK (SavingsPPKCreateDto dto){
        SavingPPK saving = new SavingPPK();
        saving.setName(dto.getName());
        saving.setComment(dto.getComment());

        saving.setStartDate(dto.getStartDate() !=null ? dto.getStartDate() : LocalDate.now());
        return  savingsRepository.save(saving);
    }



    //pobranie listy wszystkich

    public List<Saving> getAllSavings(){
        return  savingsRepository.findAll();
    }


}
