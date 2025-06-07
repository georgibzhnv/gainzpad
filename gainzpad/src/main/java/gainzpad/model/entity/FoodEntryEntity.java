package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.MealTimeEnum;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "food_entries")
public class FoodEntryEntity extends BaseEntity {

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private Double calories; // Калории на 100 г

    @Column(nullable = false)
    private Double protein; // Протеини на 100 г

    @Column(nullable = false)
    private Double carbs; // Въглехидрати на 100 г

    @Column(nullable = false)
    private Double fats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealTimeEnum mealTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDate date;

    private Double weightInGrams;

    public String getFoodName() {
        return foodName;
    }

    public FoodEntryEntity setFoodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public Double getCalories() {
        return calories;
    }

    public FoodEntryEntity setCalories(Double calories) {
        this.calories = calories;
        return this;
    }

    public Double getProtein() {
        return protein;
    }

    public FoodEntryEntity setProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    public Double getCarbs() {
        return carbs;
    }

    public FoodEntryEntity setCarbs(Double carbs) {
        this.carbs = carbs;
        return this;
    }

    public Double getFats() {
        return fats;
    }

    public FoodEntryEntity setFats(Double fats) {
        this.fats = fats;
        return this;
    }

    public MealTimeEnum getMealTime() {
        return mealTime;
    }

    public FoodEntryEntity setMealTime(MealTimeEnum mealTime) {
        this.mealTime = mealTime;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public FoodEntryEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public FoodEntryEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Double getWeightInGrams() {
        return weightInGrams;
    }

    public FoodEntryEntity setWeightInGrams(Double weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }
}
