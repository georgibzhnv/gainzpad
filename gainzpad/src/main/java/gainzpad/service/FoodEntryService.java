package gainzpad.service;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.enums.MealTimeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface FoodEntryService {


    Map<MealTimeEnum, List<FoodEntryDTO>> getMealsMap(UserEntity user, LocalDate date);
    void addFoodEntry(FoodEntryDTO dto);
    List<FoodEntryDTO> getEntriesByUserAndDate(UserEntity user, LocalDate date);
    void deleteEntry(Long id);
    Optional<FoodEntryDTO> getById(Long id);
    void editFoodEntry(FoodEntryDTO dto);
}