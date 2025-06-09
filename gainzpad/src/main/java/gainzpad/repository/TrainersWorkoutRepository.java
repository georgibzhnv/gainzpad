package gainzpad.repository;

import gainzpad.model.entity.TrainersWorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainersWorkoutRepository extends JpaRepository<TrainersWorkoutEntity,Long> {
}
