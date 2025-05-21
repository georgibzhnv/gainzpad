package gainzpad.service.impl;

import gainzpad.model.dto.WorkoutDTO;
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

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.userRepository = userRepository;
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
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // 2) Лоудваме UserEntity
        UserEntity currentUser = userRepository
                .findOneByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        // 3) Мапваме DTO → Entity
        WorkoutEntity workout = WorkoutMapper.INSTANCE.mapWorkoutDtoToEntity(workoutDTO);

        // 4) Асoциираме собственик
        workout.setUser(currentUser);

        // 5) Запазваме — Cascade.ALL ще запази и упражненията
        workoutRepository.save(workout);
    }
}
