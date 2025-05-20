package gainzpad.service;

import gainzpad.model.dto.ExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseService {
    List<ExerciseDTO> getAllExercises();
}
