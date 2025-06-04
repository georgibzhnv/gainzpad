package gainzpad.web;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.service.FoodEntryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracker")
public class FoodEntryController {

    private final FoodEntryService foodEntryService;

    public FoodEntryController(FoodEntryService foodEntryService) {
        this.foodEntryService = foodEntryService;
    }

    @GetMapping
    public String getAllFoodEntries(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("foodEntries",foodEntryService.getAllByUser(username));
        return "tracker/list";
    }

    // Show form to add a new food entry
    @GetMapping("/new")
    public String addFoodEntryForm(Model model) {
        model.addAttribute("foodEntry", new FoodEntryDTO());
        return "tracker/new";
    }

    @PostMapping("/new")
    public String addFoodEntry(@ModelAttribute("foodEntry") FoodEntryDTO foodEntryDTO,Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        foodEntryService.addFoodEntry(foodEntryDTO,username);
        return "redirect:/tracker";
    }

    @GetMapping("/{id}")
    public String viewFoodEntry(@PathVariable Long id,Model model){
        model.addAttribute("foodEntry",foodEntryService.getFoodEntryById(id)
                .orElseThrow(()->new IllegalArgumentException("Food entry not found: " + id)));
        return "tracker/view";
    }

    @PostMapping("/delete/{id}")
    public String deleteFoodEntry(@PathVariable Long id){
        foodEntryService.deleteFoodEntry(id);
        return "redirect:/tracker";
    }
}
