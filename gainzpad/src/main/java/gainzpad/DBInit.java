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
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
        initUsers();
    }

    public void init(){
        ExerciseEntity pushUp = new ExerciseEntity();
        pushUp.setName("Push Up");

        ExerciseEntity squat = new ExerciseEntity();
        squat.setName("Squat");

        exerciseRepository.saveAll(List.of(pushUp,squat));

        WorkoutEntity chestDay = new WorkoutEntity();
        chestDay.setWorkoutName("Chest day");

        WorkoutExercise workoutPushUp = new WorkoutExercise();
        workoutPushUp
                .setExercise(pushUp)
                .setSets(4)
                .setReps(12)
                .setWeight(0.0)
                .setWorkout(chestDay);
        WorkoutExercise workoutSquat = new WorkoutExercise();
        workoutSquat.setExercise(squat)
                .setSets(4)
                .setReps(12)
                .setWeight(0.0)
                .setWorkout(chestDay);

        chestDay.setWorkoutExercises(Set.of(workoutPushUp,workoutSquat));
        workoutRepository.save(chestDay);

        workoutExerciseRepository.save(workoutPushUp);
        workoutExerciseRepository.save(workoutSquat);
    }

    public void initUsers(){
        if (userRepository.count()==0){
            UserEntity admin = new UserEntity();
            admin.setEmail("gbozhinov17@gmail.com")
                    .setUsername("gbozhinov")
                    .setPassword(passwordEncoder.encode("topsecret"));

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            admin.setRoles(Set.of(adminRole,userRole));

            UserEntity user = new UserEntity();
            user.setEmail("stefani@gmail.com")
                    .setUsername("stefani")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRoles(Set.of(userRole));

            userRepository.saveAll(List.of(admin,user));
        }
    }
}
