package gainzpad.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkoutDTO {

    private Long id;
    @NotBlank
    private String exerciseName;
    @NotBlank
    private int sets;
    @NotBlank
    private int reps;
    @NotBlank
    private double weight;

}
