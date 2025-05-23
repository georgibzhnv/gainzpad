package gainzpad.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private long restTime;
    private long timeSpent;
    private boolean isCompleted;

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutExercise setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WorkoutExercise setId(Long id) {
        this.id = id;
        return this;
    }

    public long getRestTime() {
        return restTime;
    }

    public WorkoutExercise setRestTime(long restTime) {
        this.restTime = restTime;
        return this;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public WorkoutExercise setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

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
