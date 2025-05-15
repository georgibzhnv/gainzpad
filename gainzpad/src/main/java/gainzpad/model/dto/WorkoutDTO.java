package gainzpad.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkoutDTO {

    private Long id;
    private String workoutName;
    @NotBlank
    private Set<WorkoutExerciseDTO> exercises;

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

    public Set<WorkoutExerciseDTO> getExercises() {
        return exercises;
    }

    public WorkoutDTO setExercises(Set<WorkoutExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }
}
