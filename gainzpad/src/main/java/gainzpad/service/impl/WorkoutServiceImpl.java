package gainzpad.service.impl;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.model.entity.ExerciseEntity;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
import gainzpad.model.entity.SetEntity;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.mapper.WorkoutMapper;
import gainzpad.repository.ExerciseRepository;
import gainzpad.repository.UserRepository;
import gainzpad.repository.WorkoutExerciseRepository;
import gainzpad.repository.WorkoutRepository;
import gainzpad.service.WorkoutService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, UserRepository userRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.userRepository = userRepository;
        this.workoutMapper = workoutMapper;
    }

    @Override
    public List<WorkoutDTO>getAll(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutMapper::mapWorkoutEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(WorkoutDTO workoutDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        WorkoutEntity workout = new WorkoutEntity()
                .setWorkoutName(workoutDTO.getWorkoutName())
                .setUser(user);

        for (WorkoutExerciseDTO exDto : workoutDTO.getExercises()) {
            ExerciseEntity exercise;

            if (exDto.getExerciseId() != null) {
                exercise = exerciseRepository.findById(exDto.getExerciseId())
                        .orElseThrow(() -> new IllegalArgumentException("No exercise with ID=" + exDto.getExerciseId()));
            }

            else if (exDto.getNewExerciseName() != null && !exDto.getNewExerciseName().isBlank()) {
                exercise = new ExerciseEntity();
                exercise.setName(exDto.getNewExerciseName());
                // сетни и други полета ако имаш
                exercise = exerciseRepository.save(exercise);
            }

            else {
                throw new IllegalArgumentException("No exercise specified.");
            }

            WorkoutExercise we = new WorkoutExercise()
                    .setWorkout(workout)
                    .setExercise(exercise)
                    .setRestTime(exDto.getRestTime())
                    .setTimeSpent(exDto.getTimeSpent());

            List<SetEntity> setEntities = exDto.getSets().stream()
                    .map(setDTO -> new SetEntity()
                            .setReps(setDTO.getReps())
                            .setWeight(setDTO.getWeight())
                            .setCompleted(setDTO.isCompleted())
                            .setWorkoutExercise(we))
                    .collect(Collectors.toList());
            we.setSets(setEntities);
            workout.getWorkoutExercises().add(we);
        }

        workoutRepository.save(workout);
    }

    @Override
    public List<WorkoutDTO> getAllByUser(String username) {
        return workoutRepository.findAllByUser_Email(username)
                .stream()
                .map(workoutMapper::mapWorkoutEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WorkoutDTO> getById(Long id) {
        return workoutRepository.findByIdWithExercises(id)
                .map(workoutMapper::mapWorkoutEntityToDto);
    }

    @Override
    public void startWorkout(Long id) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + id + " not found"));
        workoutEntity.setStartTime(LocalDateTime.now());
        workoutEntity.setActive(true);

        workoutRepository.save(workoutEntity);
    }

    @Override
    public void finishWorkout(Long id) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + id + " not found"));
        workoutEntity.setEndTime(LocalDateTime.now());
        workoutEntity.setActive(false);

        workoutRepository.save(workoutEntity);
    }

    @Override
    public void recordSet(Long id, Long exerciseId, Long setId, Integer reps, Double weight) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + id + " not found"));

        WorkoutExercise workoutExercise = workoutEntity.getWorkoutExercises().stream()
                .filter(we->we.getId().equals(exerciseId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Exercise not found."));

        SetEntity setEntity = workoutExercise.getSets().stream()
                .filter(set->set.getId().equals(setId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Set not found."));

        setEntity.setReps(reps);
        setEntity.setWeight(weight);
        setEntity.setCompleted(true);

        workoutRepository.save(workoutEntity);
    }


    @Override
    public void startRest(Long id, long restTime) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + id + " not found"));

        for (WorkoutExercise we : workoutEntity.getWorkoutExercises()) {
            for (SetEntity set : we.getSets()) {
                if (!set.isCompleted()){
                    set.setRestTime(restTime);
                }
            }
        }
        workoutRepository.save(workoutEntity);
    }

    @Override
    public void addSet(Long workoutId, Long exerciseId) {
        WorkoutEntity workout = workoutRepository.findById(workoutId)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + workoutId + " not found"));
        WorkoutExercise exercise = workout.getWorkoutExercises().stream()
                .filter(we->we.getId().equals(exerciseId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Exercise not found."));

        SetEntity newSet = new SetEntity()
                .setReps(0)
                .setWeight(0.0)
                .setCompleted(false)
                .setWorkoutExercise(exercise);

        exercise.getSets().add(newSet);
        workoutRepository.save(workout);
    }

    @Override
    public void removeSet(Long workoutId, Long exerciseId, Long setId) {
        WorkoutEntity workout = workoutRepository.findById(workoutId)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + workoutId + " not found"));
        WorkoutExercise exercise = workout.getWorkoutExercises().stream()
                .filter(we->we.getId().equals(exerciseId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Exercise not found."));

        exercise.getSets().removeIf(set -> set.getId().equals(setId));
        workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }


}
