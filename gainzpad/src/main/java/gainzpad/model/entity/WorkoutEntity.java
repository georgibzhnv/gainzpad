package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    private String workoutName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<WorkoutExercise> workoutExercises = new HashSet<>();


    public UserEntity getUser() {
        return user;
    }

    public WorkoutEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

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
