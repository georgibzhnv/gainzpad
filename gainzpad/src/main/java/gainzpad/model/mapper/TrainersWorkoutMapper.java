package gainzpad.model.mapper;

import gainzpad.model.dto.TrainersWorkoutDTO;
import gainzpad.model.entity.TrainersWorkoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainersWorkoutMapper {

    TrainersWorkoutEntity toEntity(TrainersWorkoutDTO dto);
    TrainersWorkoutDTO toDto(TrainersWorkoutEntity entity);
}
