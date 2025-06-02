package gainzpad.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sets")
public class SetEntity extends BaseEntity {

    private int reps;
    private Double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;

    private Long restTime;
    private boolean isCompleted;


    public Long getRestTime() {
        return restTime;
    }

    public SetEntity setRestTime(Long restTime) {
        this.restTime = restTime;
        return this;
    }

    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    public SetEntity setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public SetEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }


    public int getReps() {
        return reps;
    }

    public SetEntity setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public SetEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }
}
