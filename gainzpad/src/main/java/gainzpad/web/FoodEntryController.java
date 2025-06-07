package gainzpad.web;
import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gainzpad.service.FoodEntryService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tracker")
public class FoodEntryController {

    private final FoodEntryService foodEntryService;
    private final UserRepository userRepository;

    public FoodEntryController(FoodEntryService foodEntryService, UserRepository userRepository) {
        this.foodEntryService = foodEntryService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String diary(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        LocalDate today = LocalDate.now();

        List<FoodEntryDTO> entries = foodEntryService.getEntriesByUserAndDate(user, today);
        model.addAttribute("entries", entries);

        // Калкулация на макроси по ден и по хранения
        double totalCal = 0, totalCarbs = 0, totalProtein = 0, totalFats = 0;
        for (FoodEntryDTO entry : entries) {
            double factor = entry.getWeightInGrams() / 100.0;
            totalCal += entry.getCalories() * factor;
            totalCarbs += entry.getCarbs() * factor;
            totalProtein += entry.getProtein() * factor;
            totalFats += entry.getFats() * factor;
        }
        model.addAttribute("totalCal", totalCal);
        model.addAttribute("totalCarbs", totalCarbs);
        model.addAttribute("totalProtein", totalProtein);
        model.addAttribute("totalFats", totalFats);

        // TODO: Добави цели за user (или mock стойности за тест)
        model.addAttribute("goalCalories", 2300);
        model.addAttribute("goalCarbs", 300);
        model.addAttribute("goalProtein", 150);
        model.addAttribute("goalFats", 70);
        model.addAttribute("foodEntryDTO",new FoodEntryDTO());

        return "tracker/diary";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute FoodEntryDTO foodEntryDTO, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findOneByEmail(userDetails.getUsername()).orElseThrow();
        foodEntryDTO.setUser(user);
        foodEntryDTO.setDate(LocalDate.now());
        foodEntryService.addFoodEntry(foodEntryDTO);
        return "redirect:/tracker";
    }
        // Optional: delete, edit, etc.
}
