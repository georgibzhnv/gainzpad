package gainzpad.model.entity.user;

import gainzpad.model.entity.BaseEntity;
import gainzpad.model.entity.GoalEntity;
import gainzpad.model.entity.WorkoutEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id")
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<WorkoutEntity> workouts = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private GoalEntity goal;

    public GoalEntity getGoal() {
        return goal;
    }

    public UserEntity setGoal(GoalEntity goal) {
        this.goal = goal;
        return this;
    }

    public Set<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public UserEntity setWorkouts(Set<WorkoutEntity> workouts) {
        this.workouts = workouts;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserEntity{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
