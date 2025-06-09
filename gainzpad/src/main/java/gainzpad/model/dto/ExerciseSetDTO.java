package gainzpad.model.dto;

public class ExerciseSetDTO {
    private String exerciseName;
    private String sets;

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseSetDTO setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getSets() {
        return sets;
    }

    public ExerciseSetDTO setSets(String sets) {
        this.sets = sets;
        return this;
    }
}
