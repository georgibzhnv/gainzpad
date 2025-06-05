package gainzpad.service;

import gainzpad.model.dto.GoalDTO;
import org.springframework.stereotype.Service;

@Service
public interface GoalService {

    GoalDTO createGoal(GoalDTO goalDTO, String username);

    GoalDTO getGoalByUser(String username);

    GoalDTO updateGoal(GoalDTO goalDTO,String username);
}
