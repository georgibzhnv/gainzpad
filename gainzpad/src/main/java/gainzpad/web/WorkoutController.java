package gainzpad.web;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.service.ExerciseService;
import gainzpad.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;


    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public String getAllWorkouts(Model model){

        model.addAttribute("workouts",workoutService.getAll());
        return "workouts/list";
    }

    @GetMapping("/new")
    public String newWorkout(Model model) {
        model.addAttribute("workout", new WorkoutDTO());
        model.addAttribute("allExercises", exerciseService.getAllExercises());
        return "workouts/new";
    }

    @PostMapping("/new")
    public String createWorkout(@Valid @ModelAttribute("workout") WorkoutDTO workoutDTO,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "workouts/new";
        }
        workoutService.create(workoutDTO);
        return "redirect:/workouts";
    }
}
