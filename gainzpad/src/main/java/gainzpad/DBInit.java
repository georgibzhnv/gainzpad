package gainzpad;

import gainzpad.model.entity.ExerciseEntity;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
import gainzpad.repository.ExerciseRepository;
import gainzpad.repository.WorkoutExerciseRepository;
import gainzpad.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public DBInit(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
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
}
