package gainzpad.service.impl;

import gainzpad.model.dto.GoalDTO;
import gainzpad.model.entity.GoalEntity;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.mapper.GoalMapper;
import gainzpad.repository.GoalRepository;
import gainzpad.repository.UserRepository;
import gainzpad.service.GoalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final GoalMapper goalMapper;

    public GoalServiceImpl(GoalRepository goalRepository, UserRepository userRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public GoalDTO createGoal(GoalDTO goalDTO, String email) {
        UserEntity user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GoalEntity entity = goalMapper.toEntity(goalDTO);
        entity.setUser(user);

        GoalEntity saved = goalRepository.save(entity);
        return goalMapper.toDto(saved);
    }

    @Override
    public GoalDTO getGoalByEmail(String email) {
        Optional<GoalEntity> optionalGoal = goalRepository.findByUser_Email(email);
        return optionalGoal.map(goalMapper::toDto).orElse(null);
    }

    @Override
    public GoalDTO updateGoal(GoalDTO goalDTO, String email) {
        GoalEntity goal = goalRepository.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setTargetCalories(goalDTO.getTargetCalories());
        goal.setTargetProtein(goalDTO.getTargetProtein());
        goal.setTargetCarbs(goalDTO.getTargetCarbs());
        goal.setTargetFat(goalDTO.getTargetFat());

        GoalEntity updated = goalRepository.save(goal);
        return goalMapper.toDto(updated);
    }


    @Override
    public void saveOrUpdateGoal(GoalDTO goalDTO, String email) {
        Optional<GoalEntity> optionalGoal = goalRepository.findByUser_Email(email);

        if (optionalGoal.isPresent()) {
            // Update
            GoalEntity goal = optionalGoal.get();
            goal.setTargetCalories(goalDTO.getTargetCalories());
            goal.setTargetProtein(goalDTO.getTargetProtein());
            goal.setTargetCarbs(goalDTO.getTargetCarbs());
            goal.setTargetFat(goalDTO.getTargetFat());

            goalRepository.save(goal);
        } else {
            createGoal(goalDTO, email);
        }
    }
}