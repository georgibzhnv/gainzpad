package gainzpad.service.impl;

import gainzpad.model.dto.GoalDTO;
import gainzpad.repository.GoalRepository;
import gainzpad.repository.UserRepository;
import gainzpad.service.GoalService;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalServiceImpl(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GoalDTO createGoal(GoalDTO goalDTO, String username) {
        return null;
    }

    @Override
    public GoalDTO getGoalByUser(String username) {
        return null;
    }

    @Override
    public GoalDTO updateGoal(GoalDTO goalDTO, String username) {
        return null;
    }
}
