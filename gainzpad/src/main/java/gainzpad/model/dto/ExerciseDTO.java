package gainzpad.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseDTO {

    private Long id;
    @NotBlank
    private String name;

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

}
