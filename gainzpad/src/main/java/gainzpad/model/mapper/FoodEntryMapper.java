package gainzpad.model.mapper;

import gainzpad.model.dto.FoodEntryDTO;
import gainzpad.model.entity.FoodEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodEntryMapper {

    FoodEntryMapper INSTANCE = Mappers.getMapper(FoodEntryMapper.class);

    FoodEntryDTO toDto(FoodEntryEntity foodEntryEntity);
    FoodEntryEntity toEntity(FoodEntryDTO foodEntryDTO);
}
