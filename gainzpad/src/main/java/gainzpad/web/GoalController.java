package gainzpad.web;

import gainzpad.model.dto.GoalDTO;
import gainzpad.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String viewGoal(Principal principal, Model model) {
        String email = principal.getName();
        GoalDTO goal = goalService.getGoalByEmail(email);
        if (goal == null) goal = new GoalDTO();
        model.addAttribute("goal", goal);
        return "tracker/goal/view";
    }

    @GetMapping("/create")
    public String createGoalForm(Principal principal, Model model) {
        String email = principal.getName();
        GoalDTO goal = goalService.getGoalByEmail(email);
        if (goal == null) goal = new GoalDTO();
        model.addAttribute("goal", goal);
        return "tracker/goal/create";
    }


    @PostMapping("/create")
    public String saveGoal(@ModelAttribute GoalDTO goal, Principal principal) {
        String email = principal.getName();
        goalService.saveOrUpdateGoal(goal, email);
        return "redirect:/goal";
    }
}

