package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.FutureMe;

public interface FutureMeRepository extends JpaRepository<FutureMe, Long> {

}
