package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingValue;

import java.time.LocalDate;
import java.util.Optional;

public interface SavingValueRepository extends JpaRepository<SavingValue, Long> {
    //pierwsza wartosc
    Optional<SavingValue>findFirstBySavingOrderByDateAsc(Saving saving);

    //aktualna wartosc
    Optional<SavingValue> findFirstBySavingOrderByDateDesc(Saving saving);

    //wartosc na dzien

    Optional<SavingValue> findTopBySavingAndDateLessThanEqualOrderByDateDesc(Saving saving, LocalDate date);


    //cala hisotria
    Optional<SavingValue> findAllBySavingOrderByDateDesc(Saving saving);

    //cala historia dla id
    Optional<SavingValue> findAllBySavingIdOrderByDateAsc(Long savingId);
}
