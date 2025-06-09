package gainzpad.model.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "trainers_workouts")
public class TrainersWorkoutEntity extends BaseEntity{

    private String workoutName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "workout_id")
    private List<ExerciseSetEntity> exerciseSets = new ArrayList<>();
    private String coachName;

    public String getCoachName() {
        return coachName;
    }

    public TrainersWorkoutEntity setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public TrainersWorkoutEntity setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public List<ExerciseSetEntity> getExerciseSets() {
        return exerciseSets;
    }

    public TrainersWorkoutEntity setExerciseSets(List<ExerciseSetEntity> exerciseSets) {
        this.exerciseSets = exerciseSets;
        return this;
    }
}
