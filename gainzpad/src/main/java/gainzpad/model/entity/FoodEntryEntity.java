package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "food_entries")
public class FoodEntryEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double calories;
    @Column(nullable = false)
    private Double protein;
    @Column(nullable = false)
    private Double carbs;
    @Column(nullable = false)
    private Double fats;
    @Column(nullable = false)
    private String mealTime;

    @Column(nullable = true)
    private String foodImageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getName() {
        return name;
    }

    public FoodEntryEntity setName(String name) {
        this.name = name;
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

    public String getMealTime() {
        return mealTime;
    }

    public FoodEntryEntity setMealTime(String mealTime) {
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
