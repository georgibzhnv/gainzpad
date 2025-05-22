package gainzpad.repository;

import gainzpad.model.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {

    @EntityGraph(attributePaths = {"workoutExercises", "workoutExercises.exercise"})
    List<WorkoutEntity> findAllByUser_Email(String userEmail);

    @Query("SELECT w FROM WorkoutEntity w JOIN FETCH w.workoutExercises we JOIN FETCH we.exercise WHERE w.id = :id")
    Optional<WorkoutEntity> findByIdWithExercises(@Param("id") Long id);

}
