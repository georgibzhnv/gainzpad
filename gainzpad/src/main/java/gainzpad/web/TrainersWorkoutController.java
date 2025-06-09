package gainzpad.web;

import gainzpad.service.TrainersWorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
