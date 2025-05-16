package gainzpad.model.mapper;

import gainzpad.model.dto.WorkoutExerciseDTO;
import gainzpad.model.entity.WorkoutExercise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutExerciseMapper {

    WorkoutExerciseMapper INSTANCE = Mappers.getMapper(WorkoutExerciseMapper.class);

    WorkoutExerciseDTO toDto(WorkoutExercise workoutExercise);

    WorkoutExercise toEntity(WorkoutExerciseDTO workoutExerciseDto);
}
