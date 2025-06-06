package gainzpad.service;

import gainzpad.model.dto.FoodEntryDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface FoodEntryService {

    List<FoodEntryDTO> getAllByUserAndDate(String username, LocalDateTime date);
    FoodEntryDTO addFoodEntry(FoodEntryDTO foodEntryDTO, String username);
    Optional<FoodEntryDTO> getFoodEntryById(Long id);
    void deleteFoodEntry(Long id, String username);

}
