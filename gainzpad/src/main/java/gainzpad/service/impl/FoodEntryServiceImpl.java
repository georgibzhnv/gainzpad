package gainzpad.service.impl;


import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import gainzpad.model.mapper.FoodEntryMapper;
import gainzpad.repository.FoodEntryRepository;
import gainzpad.repository.UserRepository;
import gainzpad.service.FoodEntryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodEntryServiceImpl implements FoodEntryService {

    private final FoodEntryRepository foodEntryRepository;
    private final FoodEntryMapper foodEntryMapper;
    private final UserRepository userRepository;

    public FoodEntryServiceImpl(FoodEntryRepository foodEntryRepository, FoodEntryMapper foodEntryMapper, UserRepository userRepository) {
        this.foodEntryRepository = foodEntryRepository;
        this.foodEntryMapper = foodEntryMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<FoodEntryDTO> getAllByUserAndDate(String email, LocalDate date) {
        return foodEntryRepository.findAllByUser_EmailAndDate(email, date)
                .stream()
                .map(foodEntryMapper::toDto)
                .toList();
    }

    @Override
    public FoodEntryDTO addFoodEntry(FoodEntryDTO foodEntryDTO, String email){
        FoodEntryEntity foodEntryEntity = foodEntryMapper.toEntity(foodEntryDTO);
        foodEntryEntity.setUser(userRepository.findOneByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
        if (foodEntryEntity.getDate() == null) {
            foodEntryEntity.setDate(LocalDateTime.now());
        }
        foodEntryRepository.save(foodEntryEntity);

        return foodEntryMapper.toDto(foodEntryEntity);
    }

    @Override
    public Optional<FoodEntryDTO> getFoodEntryById(Long id){
        return foodEntryRepository.findById(id)
                .map(foodEntryMapper::toDto);
    }

    @Override
    public void deleteFoodEntry(Long id, String email){
        FoodEntryEntity entry = foodEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food entry not found"));
        if (!entry.getUser().getEmail().equals(email)) {
            throw new SecurityException("Cannot delete entry of another user!");
        }
        foodEntryRepository.delete(entry);
    }
}
