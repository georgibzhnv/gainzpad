package gainzpad.model.mapper;

import gainzpad.model.dto.MacroGoalsDTO;
import gainzpad.model.entity.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MacroGoalsMapper {

    MacroGoalsDTO toDto(UserEntity user);
    UserEntity toEntity(MacroGoalsDTO macroGoalsDTO);
}
