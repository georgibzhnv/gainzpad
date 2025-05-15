package gainzpad.model.dto;

public class WorkoutExerciseDTO {

    private Long workoutId;
    private Long exerciseId;
    private int sets;
    private int reps;
    private double weight;

    public Long getWorkoutId() {
        return workoutId;
    }

    public WorkoutExerciseDTO setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public WorkoutExerciseDTO setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public WorkoutExerciseDTO setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public WorkoutExerciseDTO setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public WorkoutExerciseDTO setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
