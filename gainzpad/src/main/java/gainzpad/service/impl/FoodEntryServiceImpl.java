package gainzpad.service.impl;


import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import gainzpad.model.mapper.FoodEntryMapper;
import gainzpad.repository.FoodEntryRepository;
import gainzpad.repository.UserRepository;
import gainzpad.service.FoodEntryService;
import org.springframework.stereotype.Service;

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

    public List<FoodEntryDTO>getAllByUser(String username){
        return foodEntryRepository.findAllByUser_Email(username)
                .stream()
                .map(foodEntryMapper::toDto)
                .toList();

    }

    public FoodEntryDTO addFoodEntry(FoodEntryDTO foodEntryDTO,String username){
        FoodEntryEntity foodEntryEntity = foodEntryMapper.toEntity(foodEntryDTO);
        foodEntryEntity.setUser(userRepository.findOneByEmail(username)
                .orElseThrow(()->new RuntimeException("User not found")));

        foodEntryRepository.save(foodEntryEntity);

        return foodEntryMapper.toDto(foodEntryEntity);
    }

    public Optional<FoodEntryDTO> getFoodEntryById(Long id){
        return foodEntryRepository.findById(id)
                .map(foodEntryMapper::toDto);
    }

    public void deleteFoodEntry(Long id){
        foodEntryRepository.deleteById(id);
    }
}
