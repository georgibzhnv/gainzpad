package gainzpad.web;

import gainzpad.model.dto.GoalDTO;
import gainzpad.service.GoalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracker/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String getGoal(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        GoalDTO goalDTO = goalService.getGoalByUser(username);
        model.addAttribute("goal", goalDTO);
        return "tracker/goal/view";
    }

    @GetMapping("/new")
    public String createGoal(Model model) {
        model.addAttribute("goal", new GoalDTO());
        return "tracker/goal/create";
    }

    @PostMapping("/new")
    public String saveGoal(@ModelAttribute GoalDTO goalDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        goalService.createGoal(goalDTO, username);
        return "redirect:/goals";
    }

    @PostMapping("/update")
    public String updateGoal(@ModelAttribute GoalDTO goalDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        goalService.updateGoal(goalDTO, username);
        return "redirect:/goals";
    }
}
