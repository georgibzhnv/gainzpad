package gainzpad.web;

import gainzpad.model.dto.NutritionSummaryDTO;
import gainzpad.service.NutritionSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/tracker/nutrition-summary")
public class NutritionSummaryController {
    private final NutritionSummaryService nutritionSummaryService;

    public NutritionSummaryController(NutritionSummaryService nutritionSummaryService) {
        this.nutritionSummaryService = nutritionSummaryService;
    }

    @GetMapping
    public String showSummary(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              Principal principal, Model model) {
        if (date == null) date = LocalDate.now();
        String email = principal.getName();
        NutritionSummaryDTO summary = nutritionSummaryService.getNutritionSummary(email, date);

        model.addAttribute("summary", summary);
        model.addAttribute("date", date);
        return "nutrition-summary";
    }

}
