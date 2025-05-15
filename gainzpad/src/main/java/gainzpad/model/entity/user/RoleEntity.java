package gainzpad.model.entity.user;

import gainzpad.model.entity.BaseEntity;
import gainzpad.model.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
