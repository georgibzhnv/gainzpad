package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    private String workoutName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<WorkoutExercise> workoutExercises =new ArrayList<>();
    private boolean isActive;
    private long totalTime;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public WorkoutEntity setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public WorkoutEntity setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public WorkoutEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public WorkoutEntity setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        return this;
    }

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

    public List<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public WorkoutEntity setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
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