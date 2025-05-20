package gainzpad.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkoutExerciseDTO {

    private Long workoutId;

    @NotNull(message = "exerciseId не може да е null")
    private Long exerciseId;

    @Min(value = 1, message = "Броят серии трябва да е поне 1")
    private int sets;

    @Min(value = 1, message = "Броят повторения трябва да е поне 1")
    private int reps;

    @Min(value = 0, message = "Тежестта не може да е отрицателна")
    private double weight;

    private String newExerciseName; // добави

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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

