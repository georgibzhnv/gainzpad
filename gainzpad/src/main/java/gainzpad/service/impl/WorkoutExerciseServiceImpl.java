package gainzpad.service.impl;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.model.mapper.WorkoutExerciseMapper;
import gainzpad.model.mapper.WorkoutMapper;
import gainzpad.repository.WorkoutExerciseRepository;
import gainzpad.service.WorkoutExerciseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseServiceImpl(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }
    @Override
    public List<WorkoutExerciseDTO> getAll(){
        return workoutExerciseRepository.findAll()
                .stream()
                .map(WorkoutExerciseMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
