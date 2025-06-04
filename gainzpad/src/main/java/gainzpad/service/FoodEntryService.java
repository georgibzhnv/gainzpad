package gainzpad.service;

import gainzpad.model.dto.FoodEntryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoodEntryService {

    List<FoodEntryDTO> getAllByUser(String username);
    FoodEntryDTO addFoodEntry(FoodEntryDTO foodEntryDTO,String username);
    void deleteFoodEntry(Long id);
    Optional<FoodEntryDTO> getFoodEntryById(Long id);
}
