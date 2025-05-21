package gainzpad.web;

import gainzpad.model.dto.ExerciseDTO;
import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
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
        WorkoutDTO workout = new WorkoutDTO();

        workout.getExercises().add(new WorkoutExerciseDTO());
        model.addAttribute("workout", workout);
        model.addAttribute("allExercises", exerciseService.getAllExercises());
        return "workouts/new";
    }

    @PostMapping("/new")
    public String createWorkout(@Valid @ModelAttribute("workout") WorkoutDTO workoutDTO,
                                BindingResult bindingResult,
                                Model model) {

        // 1) Стандартна валидация на полета (workoutName, sets, reps...)
        if (bindingResult.hasErrors()) {
            model.addAttribute("allExercises", exerciseService.getAllExercises());
            return "workouts/new";
        }

        // 2) Собствена валидация: за всяко упражнение
        boolean allValid = workoutDTO.getExercises().stream().allMatch(ex ->
                // валидно, ако е избрано упражнение
                ex.getExerciseId() != null
                        // или е въведено име
                        || (ex.getNewExerciseName() != null && !ex.getNewExerciseName().isBlank())
        );

        if (!allValid) {
            model.addAttribute("allExercises", exerciseService.getAllExercises());
            model.addAttribute("errorMessage",
                    "За всяко упражнение изберете готово или напишете ново име.");
            return "workouts/new";
        }

        // 3) Създаваме всички нови упражнения по newExerciseName
        workoutDTO.getExercises().stream()
                .filter(ex -> ex.getExerciseId() == null)          // няма избрано
                .filter(ex -> !ex.getNewExerciseName().isBlank()) // има текст
                .forEach(ex -> {
                    String name = ex.getNewExerciseName().trim();
                    ExerciseDTO found = exerciseService.findByName(name)
                            .orElseGet(() -> exerciseService.createExercise(name));
                    ex.setExerciseId(found.getId());
                });

        // 4) Окончателно записваме тренировката
        workoutService.create(workoutDTO);
        return "redirect:/workouts";
    }
}
