package gainzpad.service.impl;


import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.model.mapper.FoodEntryMapper;
import gainzpad.repository.FoodEntryRepository;
import gainzpad.repository.UserRepository;
import gainzpad.service.FoodEntryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodEntryServiceImpl implements FoodEntryService {

    private final FoodEntryRepository foodEntryRepository;
    private final UserRepository userRepository;
    private final FoodEntryMapper foodEntryMapper;

    public FoodEntryServiceImpl(
            FoodEntryRepository foodEntryRepository,
            UserRepository userRepository,
            FoodEntryMapper foodEntryMapper
    ) {
        this.foodEntryRepository = foodEntryRepository;
        this.userRepository = userRepository;
        this.foodEntryMapper = foodEntryMapper;
    }

    @Override
    public void addFoodEntry(FoodEntryDTO dto) {
        FoodEntryEntity entity = foodEntryMapper.toEntity(dto);
        foodEntryRepository.save(entity);
    }

    @Override
    public List<FoodEntryDTO> getEntriesByUserAndDate(UserEntity user, LocalDate date) {
        List<FoodEntryEntity> entities = foodEntryRepository.findAllByUserAndDate(user, date);
        return entities.stream().map(foodEntryMapper::toDto).toList();
    }

    @Override
    public void deleteEntry(Long id) {
        foodEntryRepository.deleteById(id);
    }

    @Override
    public Optional<FoodEntryDTO> getById(Long id) {
        return foodEntryRepository.findById(id).map(foodEntryMapper::toDto);
    }
}
