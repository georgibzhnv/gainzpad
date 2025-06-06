package gainzpad.service.impl;

import gainzpad.model.dto.NutritionSummaryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import gainzpad.model.entity.GoalEntity;
import gainzpad.repository.FoodEntryRepository;
import gainzpad.repository.GoalRepository;
import gainzpad.service.NutritionSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NutritionSummaryServiceImpl implements NutritionSummaryService {

    private final FoodEntryRepository foodEntryRepository;
    private final GoalRepository goalRepository;

    public NutritionSummaryServiceImpl(FoodEntryRepository foodEntryRepository, GoalRepository goalRepository) {
        this.foodEntryRepository = foodEntryRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    public NutritionSummaryDTO getNutritionSummary(String email, LocalDateTime date) {
        List<FoodEntryEntity> entries = foodEntryRepository.findAllByUser_EmailAndDate(email, date);

        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;

        for (FoodEntryEntity entry : entries) {
            totalCalories += entry.getCalories();
            totalProtein += entry.getProtein();
            totalCarbs += entry.getCarbs();
            totalFats += entry.getFats();
        }

        // по-устойчиво и без NPE:
        double caloriesGoal = 0;
        double proteinGoal = 0;
        double carbsGoal = 0;
        double fatsGoal = 0;

        var goalOpt = goalRepository.findByUser_Email(email);
        if (goalOpt.isPresent()) {
            GoalEntity goal = goalOpt.get();
            caloriesGoal = goal.getTargetCalories();
            proteinGoal = goal.getTargetProtein();
            carbsGoal = goal.getTargetCarbs();
            fatsGoal = goal.getTargetFat();
        }

        NutritionSummaryDTO summary = new NutritionSummaryDTO();
        summary.setTotalCalories(totalCalories);
        summary.setTotalProtein(totalProtein);
        summary.setTotalCarbs(totalCarbs);
        summary.setTotalFats(totalFats);
        summary.setCaloriesGoal(caloriesGoal);
        summary.setProteinGoal(proteinGoal);
        summary.setCarbsGoal(carbsGoal);
        summary.setFatsGoal(fatsGoal);

        return summary;
    }
}
