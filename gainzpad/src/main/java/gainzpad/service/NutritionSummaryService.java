package gainzpad.service;

import gainzpad.model.dto.NutritionSummaryDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public interface NutritionSummaryService {
    NutritionSummaryDTO getNutritionSummary(String email, LocalDateTime date);
}
