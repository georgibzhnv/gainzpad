package gainzpad.service;

import gainzpad.model.dto.NutritionSummaryDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface NutritionSummaryService {
    NutritionSummaryDTO getNutritionSummary(String email, LocalDate date);
}
