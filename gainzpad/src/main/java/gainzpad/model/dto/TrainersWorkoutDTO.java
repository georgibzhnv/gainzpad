package gainzpad.model.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrainersWorkoutDTO {

    private Long id;
    private String workoutName;
    private Map<String,String> exerciseSets = new LinkedHashMap<>();
    private String coachName;

    public String getCoachName() {
        return coachName;
    }

    public TrainersWorkoutDTO setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TrainersWorkoutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public TrainersWorkoutDTO setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public Map<String, String> getExerciseSets() {
        return exerciseSets;
    }

    public TrainersWorkoutDTO setExerciseSets(Map<String, String> exerciseSets) {
        this.exerciseSets = exerciseSets;
        return this;
    }
}
