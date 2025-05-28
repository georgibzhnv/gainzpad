package gainzpad.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExerciseDTO {

    private Long workoutId;
    @NotNull(message = "exerciseId не може да е null")
    private Long exerciseId;

    private String newExerciseName;
    private String exerciseName;
    private long restTime;
    private long timeSpent;

    private List<SetDTO> sets = new ArrayList<>();

    public List<SetDTO> getSets() {
        return sets;
    }

    public WorkoutExerciseDTO setSets(List<SetDTO> sets) {
        this.sets = sets;
        return this;
    }

    public long getRestTime() {
        return restTime;
    }

    public WorkoutExerciseDTO setRestTime(long restTime) {
        this.restTime = restTime;
        return this;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public WorkoutExerciseDTO setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public WorkoutExerciseDTO setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getNewExerciseName() {
        return newExerciseName;
    }

    public void setNewExerciseName(String newExerciseName) {
        this.newExerciseName = newExerciseName;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

}

