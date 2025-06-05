package gainzpad.repository;

import gainzpad.model.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity,Long> {

    Optional<GoalEntity> findByUser_Email(String email);

}
