package gainzpad.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", referencedColumnName = "id")
    private WorkoutEntity workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private ExerciseEntity exercise;

    private int sets;
    private int reps;
    private double weight;

    // Гетъри и Сетъри

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public WorkoutExercise setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public WorkoutExercise setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public WorkoutExercise setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public WorkoutExercise setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public WorkoutExercise setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
