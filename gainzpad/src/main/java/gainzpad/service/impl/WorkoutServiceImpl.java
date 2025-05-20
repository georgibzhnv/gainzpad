package gainzpad.service.impl;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.entity.ExerciseEntity;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
import gainzpad.model.mapper.WorkoutMapper;
import gainzpad.repository.ExerciseRepository;
import gainzpad.repository.WorkoutExerciseRepository;
import gainzpad.repository.WorkoutRepository;
import gainzpad.service.WorkoutService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @Override
    public List<WorkoutDTO>getAll(){
        return workoutRepository.findAll()
                .stream()
                .map(WorkoutMapper.INSTANCE::mapWorkoutEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(WorkoutDTO workoutDTO) {
        WorkoutEntity workoutEntity = WorkoutMapper.INSTANCE.mapWorkoutDtoToEntity(workoutDTO);


        workoutDTO.getExercises().forEach(weDto -> {
            ExerciseEntity exerciseEntity;


            if (weDto.getNewExerciseName() != null && !weDto.getNewExerciseName().isBlank()) {

                Optional<ExerciseEntity> existing = exerciseRepository.findByNameIgnoreCase(weDto.getNewExerciseName().trim());

                if (existing.isPresent()) {
                    exerciseEntity = existing.get();
                } else {
                    // Създаване на ново упражнение
                    exerciseEntity = new ExerciseEntity();
                    exerciseEntity.setName(weDto.getNewExerciseName().trim());
                    exerciseEntity = exerciseRepository.save(exerciseEntity);
                }
            } else if (weDto.getExerciseId() != null) {
                exerciseEntity = exerciseRepository.findById(weDto.getExerciseId())
                        .orElseThrow(() -> new RuntimeException("Exercise not found: " + weDto.getExerciseId()));
            } else {
                throw new RuntimeException("Exercise information missing");
            }

            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(exerciseEntity);
            workoutExercise.setSets(weDto.getSets());
            workoutExercise.setReps(weDto.getReps());
            workoutExercise.setWeight(weDto.getWeight());
            workoutExercise.setWorkout(workoutEntity);

            workoutEntity.addExercise(workoutExercise);
        });

        workoutRepository.save(workoutEntity);
    }
}
