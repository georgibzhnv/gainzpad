package gainzpad.web;

import gainzpad.model.dto.ExerciseDTO;
import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.service.ExerciseService;
import gainzpad.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            model.addAttribute("workouts", workoutService.getAll());
        } else {
            model.addAttribute("workouts", workoutService.getAllByUser(username));
        }
        return "workouts/list";
    }

    @GetMapping("/new")
    public String newWorkout(Model model) {
        WorkoutDTO workout = new WorkoutDTO();
        workout.getExercises().add(new WorkoutExerciseDTO());

        model.addAttribute("workout", workout);
        model.addAttribute("allExercises", exerciseService.getAllExercises());
        return "workouts/new";
    }

    @GetMapping("/{id}")
    public String viewWorkout(@PathVariable Long id, Model model) {
        WorkoutDTO workoutDTO = workoutService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("Workout not found: " + id));

        model.addAttribute("workout", workoutDTO);
        return "workouts/view";
    }

    @PostMapping("/new")
    public String createWorkout(@Valid @ModelAttribute("workout") WorkoutDTO workoutDTO,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("allExercises", exerciseService.getAllExercises());
            return "workouts/new";
        }

        boolean allValid = workoutDTO.getExercises().stream().allMatch(ex ->
                ex.getExerciseId() != null
                        || (ex.getNewExerciseName() != null && !ex.getNewExerciseName().isBlank())
        );

        if (!allValid) {
            model.addAttribute("allExercises", exerciseService.getAllExercises());
            model.addAttribute("errorMessage",
                    "За всяко упражнение изберете готово или напишете ново име.");
            return "workouts/new";
        }

        // Създаваме всички нови упражнения по newExerciseName
        workoutDTO.getExercises().stream()
                .filter(ex -> ex.getExerciseId() == null)
                .filter(ex -> !ex.getNewExerciseName().isBlank())
                .forEach(ex -> {
                    String name = ex.getNewExerciseName().trim();
                    ExerciseDTO found = exerciseService.findByName(name)
                            .orElseGet(() -> exerciseService.createExercise(name));
                    ex.setExerciseId(found.getId());
                });

        // Записваме тренировката и я свързваме с текущия потребител
        workoutService.create(workoutDTO);

        // Връщаме се към списъка като използваме същия username
        return "redirect:/workouts";
    }
}
