package gainzpad.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    private String workoutName;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkoutExercise> workoutExercises = new HashSet<>();


    public String getWorkoutName() {
        return workoutName;
    }

    public WorkoutEntity setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public Set<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public WorkoutEntity setWorkoutExercises(Set<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
        return this;
    }

    public void addExercise(WorkoutExercise workoutExercise){
        workoutExercises.add(workoutExercise);
        workoutExercise.setWorkout(this);
    }

    public void removeExercise(WorkoutExercise workoutExercise){
        workoutExercises.remove(workoutExercise);
        workoutExercise.setWorkout(null);
    }
}
