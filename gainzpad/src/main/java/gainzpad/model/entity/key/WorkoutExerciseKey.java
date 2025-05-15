package gainzpad.model.entity.key;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WorkoutExerciseKey implements Serializable {

    private Long workoutId;
    private Long exerciseId;

    public WorkoutExerciseKey() {}

    public WorkoutExerciseKey(Long workoutId, Long exerciseId) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public WorkoutExerciseKey setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public WorkoutExerciseKey setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkoutExerciseKey)) return false;
        WorkoutExerciseKey that = (WorkoutExerciseKey) o;
        return Objects.equals(workoutId, that.workoutId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutId, exerciseId);
    }
}
