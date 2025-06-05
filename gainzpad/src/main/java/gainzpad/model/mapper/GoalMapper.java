package gainzpad.model.mapper;

import gainzpad.model.dto.GoalDTO;
import gainzpad.model.entity.GoalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    GoalMapper INSTANCE = Mappers.getMapper(GoalMapper.class);

    GoalDTO toDto(GoalEntity goalEntity);
    GoalEntity toEntity(GoalDTO goalDTO);
}
