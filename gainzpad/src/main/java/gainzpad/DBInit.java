package gainzpad;

import gainzpad.model.entity.*;
import gainzpad.model.entity.user.RoleEntity;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.UserRoleEnum;
import gainzpad.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrainersWorkoutRepository trainersWorkoutRepository;

    public DBInit(WorkoutRepository workoutRepository,
                  ExerciseRepository exerciseRepository,
                  WorkoutExerciseRepository workoutExerciseRepository,
                  UserRepository userRepository,
                  RoleRepository roleRepository,
                  PasswordEncoder passwordEncoder, TrainersWorkoutRepository trainersWorkoutRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.trainersWorkoutRepository = trainersWorkoutRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initUsers();
        initWorkouts();
        initTrainersWorkouts();
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
                .setExercise(pushUp);

        List<SetEntity> we1Sets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SetEntity set = new SetEntity()
                    .setReps(12)
                    .setWeight(0.0)
                    .setCompleted(false)
                    .setWorkoutExercise(we1);
            we1Sets.add(set);
        }
        we1.setSets(we1Sets);

        WorkoutExercise we2 = new WorkoutExercise()
                .setWorkout(chestDay)
                .setExercise(squat);

        List<SetEntity> we2Sets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SetEntity set = new SetEntity()
                    .setReps(15)
                    .setWeight(0.0)
                    .setCompleted(false)
                    .setWorkoutExercise(we2);
            we2Sets.add(set);
        }
        we2.setSets(we2Sets);

        chestDay.setWorkoutExercises(List.of(we1, we2));
        workoutRepository.save(chestDay);
    }

    private void initTrainersWorkouts(){
        TrainersWorkoutEntity workout = new TrainersWorkoutEntity();
        workout.setCoachName("Chris Bumstead")
                .setWorkoutName("Push Workout");

        ExerciseSetEntity exerciseSets = new ExerciseSetEntity();
        exerciseSets.setExerciseName("Bench Press")
                .setSets("2 Warm-Up sets, 3 Working sets");

        ExerciseSetEntity exerciseSets2 = new ExerciseSetEntity();
        exerciseSets2.setExerciseName("Chest flyes").setSets("2 Working sets");

        workout.setExerciseSets(List.of(exerciseSets,exerciseSets2));
        trainersWorkoutRepository.save(workout);
    }

}
