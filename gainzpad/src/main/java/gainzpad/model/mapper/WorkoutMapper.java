package gainzpad.model.mapper;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.entity.WorkoutEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutMapper {

    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);

    WorkoutEntity mapWorkoutDtoToEntity(WorkoutDTO dto);
    WorkoutDTO mapWorkoutEntityToDto(WorkoutEntity workoutEntity);
}
