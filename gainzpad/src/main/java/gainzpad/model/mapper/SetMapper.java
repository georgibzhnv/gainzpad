package gainzpad.model.mapper;

import gainzpad.model.dto.SetDTO;
import gainzpad.model.entity.SetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SetMapper {

    SetMapper INSTANCE = Mappers.getMapper(SetMapper.class);

    SetDTO toDto(SetEntity setEntity);
    SetEntity toEntity(SetDTO setDto);
}
