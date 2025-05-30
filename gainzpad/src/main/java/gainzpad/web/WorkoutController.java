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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

        if (workoutDTO.getExercises() == null) {
            workoutDTO.setExercises(new ArrayList<>());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String startTimeStr = (workoutDTO.getStartTime() != null) ? workoutDTO.getStartTime().format(formatter) : "";
        String endTimeStr = (workoutDTO.getEndTime() != null) ? workoutDTO.getEndTime().format(formatter) : "";

        model.addAttribute("workout", workoutDTO);
        model.addAttribute("exercises", workoutDTO.getExercises());
        model.addAttribute("workoutStartTime", startTimeStr);
        model.addAttribute("workoutEndTime", endTimeStr);
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

        workoutService.create(workoutDTO);
        return "redirect:/workouts";
    }


    @PostMapping("/start/{id}")
    public String startWorkout(@PathVariable Long id) {
        workoutService.startWorkout(id);
        return "redirect:/workouts/" + id;
    }

    @PostMapping("/finish/{id}")
    public String finishWorkout(@PathVariable Long id) {
        workoutService.finishWorkout(id);
        return "redirect:/workouts/" + id;
    }

    @PostMapping("/{workoutId}/exercise/{exerciseId}/recordSet")
    public String recordSet(@PathVariable Long workoutId,
                            @PathVariable Long exerciseId,
                            @RequestParam Long setId,
                            @RequestParam Integer reps,
                            @RequestParam Double weight) {
        workoutService.recordSet(workoutId, exerciseId, setId, reps, weight);
        return "redirect:/workouts/" + workoutId;
    }


    @PostMapping("/{id}/startRest")
    public String startRest(@PathVariable Long id, @RequestParam long restTime) {
        workoutService.startRest(id,restTime);
        return "redirect:/workouts/" + id;
    }
}
