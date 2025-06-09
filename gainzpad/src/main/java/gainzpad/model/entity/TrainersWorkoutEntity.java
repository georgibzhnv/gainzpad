package gainzpad.model.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "trainers_workouts")
public class TrainersWorkoutEntity extends BaseEntity{

    private String workoutName;
    @ElementCollection
    @CollectionTable(name = "trainers_workout_exercise_sets", joinColumns = @JoinColumn(name = "workout_id"))
    @MapKeyColumn(name = "exercise_name")
    @Column(name = "sets")
    private Map<String,String> exerciseSets = new LinkedHashMap<>();
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

    public Map<String, String> getExerciseSets() {
        return exerciseSets;
    }

    public TrainersWorkoutEntity setExerciseSets(Map<String, String> exerciseSets) {
        this.exerciseSets = exerciseSets;
        return this;
    }
}
