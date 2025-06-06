package gainzpad.repository;

import gainzpad.model.entity.FoodEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodEntryRepository extends JpaRepository<FoodEntryEntity,Long> {

    List<FoodEntryEntity> findAllByUser_EmailAndDate(String email, LocalDateTime date);

}
