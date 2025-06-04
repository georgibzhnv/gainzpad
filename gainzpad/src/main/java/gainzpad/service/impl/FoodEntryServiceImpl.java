package gainzpad.service.impl;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import gainzpad.model.mapper.FoodEntryMapper;
import gainzpad.repository.FoodEntryRepository;
import gainzpad.service.FoodEntryService;
import org.springframework.stereotype.Service;

@Service
public class FoodEntryServiceImpl implements FoodEntryService {

    private final FoodEntryRepository foodEntryRepository;
    private final FoodEntryMapper foodEntryMapper;

    public FoodEntryServiceImpl(FoodEntryRepository foodEntryRepository, FoodEntryMapper foodEntryMapper) {
        this.foodEntryRepository = foodEntryRepository;
        this.foodEntryMapper = foodEntryMapper;
    }

//    public FoodEntryDTO addFoodEntry(FoodEntryDTO foodEntryDTO, String username){
//        FoodEntryEntity foodEntryEntity = FoodEntryMapper.INSTANCE.toEntity(foodEntryDTO);
//
//    }
}
