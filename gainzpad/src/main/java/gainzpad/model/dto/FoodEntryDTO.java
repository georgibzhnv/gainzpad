package gainzpad.model.dto;

import gainzpad.model.enums.MealTimeEnum;

import java.time.LocalDateTime;

public class FoodEntryDTO {

    private Long id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fats;
    private Double carbs;
    private Double weightInGrams;
    private MealTimeEnum mealTime;
    private String foodImageUrl;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public FoodEntryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FoodEntryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCalories() {
        return calories;
    }

    public FoodEntryDTO setCalories(Double calories) {
        this.calories = calories;
        return this;
    }

    public Double getProtein() {
        return protein;
    }

    public FoodEntryDTO setProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    public Double getFats() {
        return fats;
    }

    public FoodEntryDTO setFats(Double fats) {
        this.fats = fats;
        return this;
    }

    public Double getCarbs() {
        return carbs;
    }

    public FoodEntryDTO setCarbs(Double carbs) {
        this.carbs = carbs;
        return this;
    }

    public Double getWeightInGrams() {
        return weightInGrams;
    }

    public FoodEntryDTO setWeightInGrams(Double weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public MealTimeEnum getMealTime() {
        return mealTime;
    }

    public FoodEntryDTO setMealTime(MealTimeEnum mealTime) {
        this.mealTime = mealTime;
        return this;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public FoodEntryDTO setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public FoodEntryDTO setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
