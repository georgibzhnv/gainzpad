package gainzpad.repository;

import gainzpad.model.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity,Long> {

    GoalEntity findByUser_Id(Long userId);
}
