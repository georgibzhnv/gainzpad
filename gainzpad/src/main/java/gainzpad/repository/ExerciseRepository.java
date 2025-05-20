package gainzpad.repository;

import gainzpad.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {
    Optional<ExerciseEntity> findByNameIgnoreCase(String name);
}
