package gainzpad.web;

import gainzpad.model.dto.GoalDTO;
import gainzpad.model.dto.NutritionSummaryDTO;
import gainzpad.service.GoalService;
import gainzpad.service.NutritionSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/nutrition-summary")
public class NutritionSummaryController {

    private final NutritionSummaryService nutritionSummaryService;
    private final GoalService goalService;

    public NutritionSummaryController(NutritionSummaryService nutritionSummaryService, GoalService goalService) {
        this.nutritionSummaryService = nutritionSummaryService;
        this.goalService = goalService;
    }

    @GetMapping
    public String showSummary(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date,
                              Principal principal, Model model) {
        if (date == null) date = LocalDateTime.now();
        String email = principal.getName();

        // Вземаме хранителното обобщение за даден ден
        NutritionSummaryDTO summary = nutritionSummaryService.getNutritionSummary(email, date);

        // Вземаме целите на потребителя за деня
        GoalDTO goal = goalService.getGoalByEmail(email);

        model.addAttribute("summary", summary);
        model.addAttribute("goal", goal);
        model.addAttribute("date", date);

        return "tracker/summary";
    }

}
