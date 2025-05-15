package gainzpad.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private int sets;
    @NotBlank
    private int reps;
    @NotBlank
    private double weight;

    public Long getId() {
        return id;
    }

    public ExerciseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public ExerciseDTO setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ExerciseDTO setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public ExerciseDTO setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
