package gainzpad.model.dto;

public class NutritionSummaryDTO {
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFats;

    private double caloriesGoal;
    private double proteinGoal;
    private double carbsGoal;
    private double fatsGoal;

    public double getCaloriesLeft() { return caloriesGoal - totalCalories; }
    public double getProteinLeft() { return proteinGoal - totalProtein; }
    public double getCarbsLeft() { return carbsGoal - totalCarbs; }
    public double getFatsLeft() { return fatsGoal - totalFats; }

    public double getTotalCalories() {
        return totalCalories;
    }

    public NutritionSummaryDTO setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
        return this;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public NutritionSummaryDTO setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
        return this;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public NutritionSummaryDTO setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
        return this;
    }

    public double getTotalFats() {
        return totalFats;
    }

    public NutritionSummaryDTO setTotalFats(double totalFats) {
        this.totalFats = totalFats;
        return this;
    }

    public double getCaloriesGoal() {
        return caloriesGoal;
    }

    public NutritionSummaryDTO setCaloriesGoal(double caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
        return this;
    }

    public double getProteinGoal() {
        return proteinGoal;
    }

    public NutritionSummaryDTO setProteinGoal(double proteinGoal) {
        this.proteinGoal = proteinGoal;
        return this;
    }

    public double getCarbsGoal() {
        return carbsGoal;
    }

    public NutritionSummaryDTO setCarbsGoal(double carbsGoal) {
        this.carbsGoal = carbsGoal;
        return this;
    }

    public double getFatsGoal() {
        return fatsGoal;
    }

    public NutritionSummaryDTO setFatsGoal(double fatsGoal) {
        this.fatsGoal = fatsGoal;
        return this;
    }
}
