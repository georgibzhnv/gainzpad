package gainzpad.model.mapper;

import gainzpad.model.dto.WorkoutDTO;
import gainzpad.model.entity.WorkoutEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkoutExerciseMapper.class})
public interface WorkoutMapper {


    @Mapping(source = "workoutExercises", target = "exercises")
    WorkoutDTO mapWorkoutEntityToDto(WorkoutEntity workoutEntity);

    WorkoutEntity mapWorkoutDtoToEntity(WorkoutDTO dto);
}
