package gainzpad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private int sets;
    @NotBlank
    private int reps;
    @NotBlank
    private double weight;

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public ExerciseEntity setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ExerciseEntity setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public ExerciseEntity setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
