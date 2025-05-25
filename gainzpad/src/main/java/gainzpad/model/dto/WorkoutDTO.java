package gainzpad.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkoutDTO {
    private Long id;

    @NotBlank(message = "Името на тренировката е задължително")
    private String workoutName;

    @NotEmpty(message = "Трябва да има поне едно упражнение")
    private List<WorkoutExerciseDTO> exercises = new ArrayList<>();
    private boolean isActive;
    private long totalTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public WorkoutDTO setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public WorkoutDTO setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public WorkoutDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public WorkoutDTO setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WorkoutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public WorkoutDTO setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public List<WorkoutExerciseDTO> getExercises() {
        return exercises;
    }

    public WorkoutDTO setExercises(List<WorkoutExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }
}
