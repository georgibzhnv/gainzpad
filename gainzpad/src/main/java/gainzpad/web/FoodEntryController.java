package gainzpad.web;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.service.FoodEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/tracker")
public class FoodEntryController {

    private final FoodEntryService foodEntryService;

    public FoodEntryController(FoodEntryService foodEntryService) {
        this.foodEntryService = foodEntryService;
    }

    // Списък с храненията за конкретен ден
    @GetMapping
    public String listEntries(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date,
                              Principal principal, Model model) {
        if (date == null) date = LocalDateTime.now();
        String email = principal.getName();
        model.addAttribute("entries", foodEntryService.getAllByUserAndDate(email, date));
        model.addAttribute("date", date);
        return "tracker/list";
    }

    // Форма за добавяне
    @GetMapping("/add")
    public String addEntryForm(Model model) {
        model.addAttribute("foodEntry", new FoodEntryDTO());
        return "tracker/add";
    }

    // Записва нова храна
    @PostMapping("/add")
    public String addEntry(@ModelAttribute FoodEntryDTO foodEntry, Principal principal) {
        String email = principal.getName();
        foodEntryService.addFoodEntry(foodEntry, email);
        return "redirect:/tracker";
    }

    // Изтриване на храна
    @PostMapping("/{id}/delete")
    public String deleteEntry(@PathVariable Long id, Principal principal) {
        String email = principal.getName();
        foodEntryService.deleteFoodEntry(id, email);
        return "redirect:/tracker";
    }
}
