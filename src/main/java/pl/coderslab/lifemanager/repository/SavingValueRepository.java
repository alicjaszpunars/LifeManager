package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingValue;

import java.util.Optional;

public interface SavingValueRepository extends JpaRepository<SavingValue, Long> {
    //pierwsza wartosc
    Optional<SavingValue>findFirstBySavingOrderByValueDateAsc(Saving saving);

    //aktualna wartosc
    Optional<SavingValue> findFirstBySavingOrderByValueDateDesc(Saving saving);

    //cala hisotria
    Optional<SavingValue> findAllBySavingOrderByValueDateDesc(Saving saving);

    //cala historia dla id
    Optional<SavingValue> findAllBySavingIdOrderByValueDateAsc(Long savingId);
}
