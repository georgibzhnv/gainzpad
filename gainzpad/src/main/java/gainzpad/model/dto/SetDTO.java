package gainzpad.model.dto;


public class SetDTO {

    private Long id;
    private Integer reps;
    private Double weight;
    private boolean isCompleted;
    private Long restTime;

    public Long getId() {
        return id;
    }

    public SetDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRestTime() {
        return restTime;
    }

    public SetDTO setRestTime(Long restTime) {
        this.restTime = restTime;
        return this;
    }

    public Integer getReps() {
        return reps;
    }

    public SetDTO setReps(Integer reps) {
        this.reps = reps;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public SetDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public SetDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

}

