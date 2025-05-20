package gainzpad.model.mapper;

import gainzpad.model.dto.ExerciseDTO;
import gainzpad.model.entity.ExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDTO toDto(ExerciseEntity entity);

    ExerciseEntity toEntity(ExerciseDTO dto);
}
