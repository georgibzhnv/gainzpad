package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.MealTimeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_entries")
public class FoodEntryEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double calories; // Калории на 100 г

    @Column(nullable = false)
    private Double protein; // Протеини на 100 г

    @Column(nullable = false)
    private Double carbs; // Въглехидрати на 100 г

    @Column(nullable = false)
    private Double fats;

    @Column(nullable = false)
    private Double weightInGrams; // Тегло на храната, което потребителят въвежда

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealTimeEnum mealTime; // Време на хранене (напр. "Закуска")

    @Column(nullable = true)
    private String foodImageUrl; // URL за изображение на храната

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime date;

    public Double getWeightInGrams() {
        return weightInGrams;
    }

    public FoodEntryEntity setWeightInGrams(Double weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public FoodEntryEntity setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getName() {
        return name;
    }

    public FoodEntryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCalories() {
        return calories * (weightInGrams / 100); // Пресмятане на калориите за избраното тегло
    }

    public FoodEntryEntity setCalories(Double calories) {
        this.calories = calories;
        return this;
    }

    public Double getProtein() {
        return protein * (weightInGrams / 100); // Протеини за избраното тегло
    }

    public FoodEntryEntity setProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    public Double getCarbs() {
        return carbs * (weightInGrams / 100); // Въглехидрати за избраното тегло
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

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public FoodEntryEntity setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public FoodEntryEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
