package gainzpad.model.dto;

import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.MealTimeEnum;

import java.time.LocalDate;

public class FoodEntryDTO {

    private Long id;
    private String foodName;
    private Double calories; // Калории на 100 г
    private Double protein; // Протеини на 100 г
    private Double carbs; // Въглехидрати на 100 г
    private Double fats;
    private MealTimeEnum mealTime;
    private UserEntity user;
    private LocalDate date;
    private Double weightInGrams;

    public Long getId() {
        return id;
    }

    public FoodEntryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFoodName() {
        return foodName;
    }

    public FoodEntryDTO setFoodName(String foodName) {
        this.foodName = foodName;
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

    public Double getCarbs() {
        return carbs;
    }

    public FoodEntryDTO setCarbs(Double carbs) {
        this.carbs = carbs;
        return this;
    }

    public Double getFats() {
        return fats;
    }

    public FoodEntryDTO setFats(Double fats) {
        this.fats = fats;
        return this;
    }

    public MealTimeEnum getMealTime() {
        return mealTime;
    }

    public FoodEntryDTO setMealTime(MealTimeEnum mealTime) {
        this.mealTime = mealTime;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public FoodEntryDTO setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public FoodEntryDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Double getWeightInGrams() {
        return weightInGrams;
    }

    public FoodEntryDTO setWeightInGrams(Double weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }
}

