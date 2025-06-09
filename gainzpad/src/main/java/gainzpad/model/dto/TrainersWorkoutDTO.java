package gainzpad.model.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrainersWorkoutDTO {

    private Long id;
    private String workoutName;
    private List<ExerciseSetDTO>exerciseSets=new ArrayList<>();
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

    public List<ExerciseSetDTO> getExerciseSets() {
        return exerciseSets;
    }

    public TrainersWorkoutDTO setExerciseSets(List<ExerciseSetDTO> exerciseSets) {
        this.exerciseSets = exerciseSets;
        return this;
    }
}
