package gainzpad.service;

import gainzpad.model.dto.WorkoutExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkoutExerciseService {

    List<WorkoutExerciseDTO>getAll();
}
