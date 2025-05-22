package gainzpad;

import gainzpad.model.entity.ExerciseEntity;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
import gainzpad.model.entity.user.RoleEntity;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.UserRoleEnum;
import gainzpad.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(WorkoutRepository workoutRepository,
                  ExerciseRepository exerciseRepository,
                  WorkoutExerciseRepository workoutExerciseRepository,
                  UserRepository userRepository,
                  RoleRepository roleRepository,
                  PasswordEncoder passwordEncoder) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initUsers();
        initWorkouts();
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity()
                    .setEmail("gbozhinov17@gmail.com")
                    .setUsername("gbozhinov")
                    .setPassword(passwordEncoder.encode("topsecret"));
            RoleEntity adminRole = new RoleEntity().setRole(UserRoleEnum.ADMIN);
            RoleEntity userRole  = new RoleEntity().setRole(UserRoleEnum.USER);
            admin.setRoles(Set.of(adminRole, userRole));

            UserEntity user = new UserEntity()
                    .setEmail("stefani@gmail.com")
                    .setUsername("stefani")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRoles(Set.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    private void initWorkouts() {
        UserEntity admin = userRepository
                .findOneByEmail("gbozhinov17@gmail.com")
                .orElseThrow(() -> new IllegalStateException("Admin not found"));

        ExerciseEntity pushUp = exerciseRepository.findByName("Push Up")
                .orElseGet(() -> exerciseRepository.save(new ExerciseEntity().setName("Push Up")));

        ExerciseEntity squat = exerciseRepository.findByName("Squat")
                .orElseGet(() -> exerciseRepository.save(new ExerciseEntity().setName("Squat")));

        WorkoutEntity chestDay = new WorkoutEntity()
                .setWorkoutName("Chest Day")
                .setUser(admin);

        WorkoutExercise we1 = new WorkoutExercise()
                .setWorkout(chestDay)
                .setExercise(pushUp)
                .setSets(4).setReps(12).setWeight(0.0);

        WorkoutExercise we2 = new WorkoutExercise()
                .setWorkout(chestDay)
                .setExercise(squat)
                .setSets(4).setReps(12).setWeight(0.0);

        chestDay.setWorkoutExercises(Set.of(we1, we2));

        workoutRepository.save(chestDay);
    }

}
