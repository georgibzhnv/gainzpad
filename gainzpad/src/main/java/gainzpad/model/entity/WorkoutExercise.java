package gainzpad.model.entity;
import gainzpad.model.entity.key.WorkoutExerciseKey;
import jakarta.persistence.*;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @EmbeddedId
    private WorkoutExerciseKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workoutId")
    private WorkoutEntity workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exerciseId")
    private ExerciseEntity exercise;

    private int sets;
    private int reps;
    private double weight;

    public WorkoutExercise() {}

    // getters и setters

    public WorkoutExerciseKey getId() {
        return id;
    }

    public void setId(WorkoutExerciseKey id) {
        this.id = id;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
