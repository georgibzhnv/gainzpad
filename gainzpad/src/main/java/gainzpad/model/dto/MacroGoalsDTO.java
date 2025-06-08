package gainzpad.model.dto;

public class MacroGoalsDTO {

    private Integer goalCalories;
    private Integer goalCarbs;
    private Integer goalProtein;
    private Integer goalFat;

    public Integer getGoalCalories() {
        return goalCalories;
    }

    public MacroGoalsDTO setGoalCalories(Integer goalCalories) {
        this.goalCalories = goalCalories;
        return this;
    }

    public Integer getGoalCarbs() {
        return goalCarbs;
    }

    public MacroGoalsDTO setGoalCarbs(Integer goalCarbs) {
        this.goalCarbs = goalCarbs;
        return this;
    }

    public Integer getGoalProtein() {
        return goalProtein;
    }

    public MacroGoalsDTO setGoalProtein(Integer goalProtein) {
        this.goalProtein = goalProtein;
        return this;
    }

    public Integer getGoalFat() {
        return goalFat;
    }

    public MacroGoalsDTO setGoalFat(Integer goalFat) {
        this.goalFat = goalFat;
        return this;
    }
}
