package gainzpad.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_sets")
public class ExerciseSetEntity extends BaseEntity{

    private String exerciseName;
    private String sets;

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseSetEntity setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getSets() {
        return sets;
    }

    public ExerciseSetEntity setSets(String sets) {
        this.sets = sets;
        return this;
    }
}
