package gainzpad.web;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.dto.GoalDTO;
import gainzpad.service.FoodEntryService;
import gainzpad.service.GoalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/tracker")
public class FoodEntryController {

    private final FoodEntryService foodEntryService;
    private final GoalService goalService; // добавяме GoalService за целите

    public FoodEntryController(FoodEntryService foodEntryService, GoalService goalService) {
        this.foodEntryService = foodEntryService;
        this.goalService = goalService;
    }

    // Списък с храненията за конкретен ден
    @GetMapping
    public String listEntries(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date,
                              Principal principal, Model model) {
        if (date == null) date = LocalDateTime.now();
        String email = principal.getName();

        // Вземаме всички хранения за даден ден
        var entries = foodEntryService.getAllByUserAndDate(email, date);

        // Изчисляваме изядените калории
        double caloriesConsumed = entries.stream().mapToDouble(FoodEntryDTO::getCalories).sum();

        // Вземаме целите на потребителя за деня
        GoalDTO goal = goalService.getGoalByEmail(email);
        double caloriesGoal = goal != null ? goal.getTargetCalories() : 2000; // Ако няма зададена цел, по подразбиране 2000

        // Изчисляваме процента на изядените калории
        double progressPercentage = (caloriesConsumed / caloriesGoal) * 100;

        model.addAttribute("entries", entries);
        model.addAttribute("caloriesConsumed", caloriesConsumed);
        model.addAttribute("caloriesGoal", caloriesGoal);
        model.addAttribute("progressPercentage", progressPercentage);
        model.addAttribute("date", date);

        return "tracker/overview"; // Използваме новия темплейт overview
    }

    @GetMapping("/add")
    public String addEntryForm(Model model) {
        model.addAttribute("foodEntry", new FoodEntryDTO());
        return "tracker/add";
    }

    @PostMapping("/add")
    public String addEntry(@ModelAttribute FoodEntryDTO foodEntry, Principal principal) {
        String email = principal.getName();

        foodEntryService.addFoodEntry(foodEntry, email);

        return "redirect:/tracker";
    }

    // Изтриване на храна
    @PostMapping("/{id}/delete")
    public String deleteEntry(@PathVariable Long id, Principal principal) {
        String email = principal.getName();
        foodEntryService.deleteFoodEntry(id, email);
        return "redirect:/tracker";
    }
}
