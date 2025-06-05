package gainzpad.model.dto;

public class GoalDTO {

    private Double targetCalories;
    private Double targetProtein;
    private Double targetFat;
    private Double targetCarbs;

    public Double getTargetCalories() {
        return targetCalories;
    }

    public GoalDTO setTargetCalories(Double targetCalories) {
        this.targetCalories = targetCalories;
        return this;
    }

    public Double getTargetProtein() {
        return targetProtein;
    }

    public GoalDTO setTargetProtein(Double targetProtein) {
        this.targetProtein = targetProtein;
        return this;
    }

    public Double getTargetFat() {
        return targetFat;
    }

    public GoalDTO setTargetFat(Double targetFat) {
        this.targetFat = targetFat;
        return this;
    }

    public Double getTargetCarbs() {
        return targetCarbs;
    }

    public GoalDTO setTargetCarbs(Double targetCarbs) {
        this.targetCarbs = targetCarbs;
        return this;
    }
}