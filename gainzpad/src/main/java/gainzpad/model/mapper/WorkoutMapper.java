package gainzpad.model.mapper;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.model.entity.WorkoutEntity;
import gainzpad.model.entity.WorkoutExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkoutExerciseMapper.class})
public interface WorkoutMapper {

    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);

    WorkoutEntity mapWorkoutDtoToEntity(WorkoutDTO dto);
    WorkoutDTO mapWorkoutEntityToDto(WorkoutEntity workoutEntity);

}
