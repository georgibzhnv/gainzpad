package gainzpad.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    private String exerciseName;
    private int sets;
    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getExerciseName() {
        return exerciseName;
    }

    public WorkoutEntity setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public WorkoutEntity setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public WorkoutEntity setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public WorkoutEntity setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public WorkoutEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
