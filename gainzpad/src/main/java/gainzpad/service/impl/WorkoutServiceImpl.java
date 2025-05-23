package gainzpad.service.impl;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.model.entity.ExerciseEntity;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
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
        // 1) Намираме текущия потребител
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        // 2) Създаваме WorkoutEntity
        WorkoutEntity workout = new WorkoutEntity()
                .setWorkoutName(workoutDTO.getWorkoutName())
                .setUser(user);

        // 3) За всяко упражнение в DTO-то – създаваме WorkoutExercise и го вкарваме в WorkoutEntity
        for (WorkoutExerciseDTO exDto : workoutDTO.getExercises()) {
            // Зареждаме ExerciseEntity (трябва да е налице по ID)
            ExerciseEntity exercise = exerciseRepository.findById(exDto.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("Няма упражнение с ID=" + exDto.getExerciseId()));

            // Създаваме моста WorkoutExercise
            WorkoutExercise we = new WorkoutExercise()
                    .setWorkout(workout)      // важно за двупосочната връзка
                    .setExercise(exercise)
                    .setSets(exDto.getSets())
                    .setReps(exDto.getReps())
                    .setWeight(exDto.getWeight());

            // Добавяме към WorkoutEntity
            workout.getWorkoutExercises().add(we);
        }

        // 4) Записваме – Hibernate ще вкара редове и в workouts, и в workout_exercises
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
    public void addSets(Long id, WorkoutExerciseDTO workoutExerciseDTO) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout with id=" + id + " not found"));

        ExerciseEntity exercise = exerciseRepository.findById(workoutExerciseDTO.getExerciseId())
                .orElseThrow(()->new IllegalArgumentException("Exercise with id=" + workoutExerciseDTO.getExerciseId() + " not found"));

        WorkoutExercise workoutExercise = new WorkoutExercise()
                .setWorkout(workoutEntity)
                .setExercise(exercise)
                .setSets(workoutExerciseDTO.getSets())
                .setReps(workoutExerciseDTO.getReps())
                .setWeight(workoutExerciseDTO.getWeight())
                .setRestTime(workoutExerciseDTO.getRestTime())
                .setTimeSpent(workoutExerciseDTO.getTimeSpent());

        workoutEntity.getWorkoutExercises().add(workoutExercise);
        workoutRepository.save(workoutEntity);
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
        workoutEntity.setStartTime(LocalDateTime.now());
        workoutEntity.setActive(false);

        workoutRepository.save(workoutEntity);
    }


}
