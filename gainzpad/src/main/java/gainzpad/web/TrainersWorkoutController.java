package gainzpad.web;

import gainzpad.model.dto.ExerciseSetDTO;
import gainzpad.model.dto.TrainersWorkoutDTO;
import gainzpad.service.TrainersWorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainers-workouts")
public class TrainersWorkoutController {

    private final TrainersWorkoutService trainersWorkoutService;

    public TrainersWorkoutController(TrainersWorkoutService trainersWorkoutService) {
        this.trainersWorkoutService = trainersWorkoutService;
    }

    @GetMapping
    public String getAllWorkouts(Model model){
        model.addAttribute("workouts", trainersWorkoutService.getAll());
        return "workouts/list-trainers";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    @GetMapping("/new")
    public String newWorkout(Model model){
        TrainersWorkoutDTO dto = new TrainersWorkoutDTO();
        dto.getExerciseSets().add(new ExerciseSetDTO());
        model.addAttribute("trainersWorkoutDTO", dto);
        return "workouts/new-trainers";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    @PostMapping("/new")
    public String saveWorkout(@ModelAttribute TrainersWorkoutDTO trainersWorkoutDTO) {
        trainersWorkoutService.save(trainersWorkoutDTO);
        return "redirect:/trainers-workouts";
    }

    @GetMapping("/{id}")
    public String viewWorkout(@PathVariable Long id, Model model) {
        TrainersWorkoutDTO workout = trainersWorkoutService.getById(id);
        model.addAttribute("workout", workout);
        return "workouts/view-trainers";
    }

    @PostMapping("/delete/{id}")
    public String deleteWorkout(@PathVariable Long id){
        trainersWorkoutService.deleteById(id);
        return "redirect:/trainers-workouts";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editWorkout(@PathVariable Long id, Model model){
        TrainersWorkoutDTO workout = trainersWorkoutService.getById(id);
        model.addAttribute("trainersWorkoutDTO", workout);
        return "workouts/edit-trainers";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateWorkout(@PathVariable Long id, @ModelAttribute TrainersWorkoutDTO trainersWorkoutDTO) {
        trainersWorkoutService.update(id, trainersWorkoutDTO);
        return "redirect:/trainers-workouts";
    }
}
