package gainzpad.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    private String workoutName;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<ExerciseEntity> exercises;

    public String getWorkoutName() {
        return workoutName;
    }

    public WorkoutEntity setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public WorkoutEntity setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }
}
