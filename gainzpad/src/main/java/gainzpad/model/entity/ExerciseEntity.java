package gainzpad.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{

    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }
}
