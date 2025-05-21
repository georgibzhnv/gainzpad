package gainzpad.service;

import gainzpad.model.dto.ExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExerciseService {
    List<ExerciseDTO> getAllExercises();
    ExerciseDTO createExercise(String name);
    Optional<ExerciseDTO> findByName(String name);
}
