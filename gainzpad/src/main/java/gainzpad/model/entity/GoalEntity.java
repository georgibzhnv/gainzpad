package gainzpad.model.entity;

import gainzpad.model.entity.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "goals")
public class GoalEntity extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private Double targetCalories;
    private Double targetProtein;
    private Double targetFat;
    private Double targetCarbs;

    public UserEntity getUser() {
        return user;
    }

    public GoalEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Double getTargetCalories() {
        return targetCalories;
    }

    public GoalEntity setTargetCalories(Double targetCalories) {
        this.targetCalories = targetCalories;
        return this;
    }

    public Double getTargetProtein() {
        return targetProtein;
    }

    public GoalEntity setTargetProtein(Double targetProtein) {
        this.targetProtein = targetProtein;
        return this;
    }

    public Double getTargetFat() {
        return targetFat;
    }

    public GoalEntity setTargetFat(Double targetFat) {
        this.targetFat = targetFat;
        return this;
    }

    public Double getTargetCarbs() {
        return targetCarbs;
    }

    public GoalEntity setTargetCarbs(Double targetCarbs) {
        this.targetCarbs = targetCarbs;
        return this;
    }
}
