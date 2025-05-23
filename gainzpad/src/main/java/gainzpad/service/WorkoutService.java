package gainzpad.service;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WorkoutService {
    List<WorkoutDTO>getAll();

    void create(WorkoutDTO workoutDTO);

    List<WorkoutDTO>getAllByUser(String username);
    Optional<WorkoutDTO>getById(Long id);
    void startWorkout(Long id);
    void finishWorkout(Long id);
    void recordSet(Long id, WorkoutExerciseDTO workoutExerciseDTO);
    void startRest(Long id,long restTime);
}
