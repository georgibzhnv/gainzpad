package gainzpad.repository;

import gainzpad.model.entity.FoodEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodEntryRepository extends JpaRepository<FoodEntryEntity,Long> {
}
