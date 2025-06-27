package gainzpad.web;
import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.dto.MacroGoalsDTO;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.MealTimeEnum;
import gainzpad.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gainzpad.service.FoodEntryService;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/tracker")
public class FoodEntryController {

    private final FoodEntryService foodEntryService;
    private final UserRepository userRepository;

    public FoodEntryController(FoodEntryService foodEntryService, UserRepository userRepository) {
        this.foodEntryService = foodEntryService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String diary(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        LocalDate today = LocalDate.now();

        List<FoodEntryDTO> entries = foodEntryService.getEntriesByUserAndDate(user, today);
        model.addAttribute("entries", entries);

        Map<MealTimeEnum, List<FoodEntryDTO>> mealsMap = new EnumMap<>(MealTimeEnum.class);
        for (MealTimeEnum meal : MealTimeEnum.values()) {
            mealsMap.put(meal, new ArrayList<>());
        }
        for (FoodEntryDTO entry : entries) {
            mealsMap.get(entry.getMealTime()).add(entry);
        }
        model.addAttribute("mealsMap", mealsMap);

        model.addAttribute("mealTimes", List.of("BREAKFAST", "LUNCH", "DINNER", "SNACK"));

        // Калкулация на макроси по ден и по хранения
        double totalCal = 0, totalCarbs = 0, totalProtein = 0, totalFats = 0;
        for (FoodEntryDTO entry : entries) {
            double factor = entry.getWeightInGrams() / 100.0;
            totalCal += entry.getCalories() * factor;
            totalCarbs += entry.getCarbs() * factor;
            totalProtein += entry.getProtein() * factor;
            totalFats += entry.getFats() * factor;
        }
        model.addAttribute("totalCal", totalCal);
        model.addAttribute("totalCarbs", totalCarbs);
        model.addAttribute("totalProtein", totalProtein);
        model.addAttribute("totalFats", totalFats);

        Map<String, double[]> macrosByMeal = new HashMap<>();
        for (MealTimeEnum meal : MealTimeEnum.values()) {
            double cal = 0, carb = 0, protein = 0, fats = 0;
            List<FoodEntryDTO> mealEntries = mealsMap.get(meal);
            if (mealEntries != null) {
                for (FoodEntryDTO entry : mealEntries) {
                    double factor = entry.getWeightInGrams() / 100.0;
                    cal += entry.getCalories() * factor;
                    carb += entry.getCarbs() * factor;
                    protein += entry.getProtein() * factor;
                    fats += entry.getFats() * factor;
                }
            }
            macrosByMeal.put(meal.name(), new double[]{cal, carb, protein, fats});
        }
        model.addAttribute("macrosByMeal", macrosByMeal);


        model.addAttribute("goalCalories", user.getGoalCalories() != null ? user.getGoalCalories() : 2000);
        model.addAttribute("goalCarbs", user.getGoalCarbs() != null ? user.getGoalCarbs() : 250);
        model.addAttribute("goalProtein", user.getGoalProtein() != null ? user.getGoalProtein() : 100);
        model.addAttribute("goalFats", user.getGoalFat() != null ? user.getGoalFat() : 60);

        MacroGoalsDTO macroGoals = new MacroGoalsDTO();
        macroGoals.setGoalCalories(user.getGoalCalories());
        macroGoals.setGoalCarbs(user.getGoalCarbs());
        macroGoals.setGoalProtein(user.getGoalProtein());
        macroGoals.setGoalFat(user.getGoalFat());
        model.addAttribute("macroGoals", macroGoals);

        model.addAttribute("foodEntryDTO",new FoodEntryDTO());

        return "tracker/diary";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute FoodEntryDTO foodEntryDTO, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        foodEntryDTO.setUser(user);
        foodEntryDTO.setDate(LocalDate.now());
        foodEntryService.addFoodEntry(foodEntryDTO);
        return "redirect:/tracker";
    }

    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        foodEntryService.deleteEntry(id);
        return "redirect:/tracker";
    }

    @PostMapping("/edit")
    public String editFood(@ModelAttribute FoodEntryDTO foodEntryDTO,@AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        foodEntryDTO.setUser(user);
        foodEntryDTO.setDate(LocalDate.now());
        foodEntryService.editFoodEntry(foodEntryDTO);
        return "redirect:/tracker";
    }

    @PostMapping("/set-goals")
    public String setGoals(@ModelAttribute MacroGoalsDTO macroGoalsDTO,
                           @AuthenticationPrincipal UserDetails userDetails){
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        user.setGoalCalories(macroGoalsDTO.getGoalCalories())
                .setGoalCarbs(macroGoalsDTO.getGoalCarbs())
                .setGoalFat(macroGoalsDTO.getGoalFat())
                .setGoalProtein(macroGoalsDTO.getGoalProtein());
        userRepository.save(user);
        return "redirect:/tracker";
    }
}