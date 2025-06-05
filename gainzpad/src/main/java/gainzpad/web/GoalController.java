package gainzpad.web;

import gainzpad.model.dto.GoalDTO;
import gainzpad.service.GoalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tracker/goals")
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
        return "goal-view";
    }

    @GetMapping("/edit")
    public String editGoalForm(Principal principal, Model model) {
        String email = principal.getName();
        GoalDTO goal = goalService.getGoalByEmail(email);
        if (goal == null) goal = new GoalDTO();
        model.addAttribute("goal", goal);
        return "goal-edit";
    }

    @PostMapping("/edit")
    public String editGoal(@ModelAttribute GoalDTO goal, Principal principal) {
        String email = principal.getName();
        goalService.saveOrUpdateGoal(goal, email);
        return "redirect:/goal";
    }
}

