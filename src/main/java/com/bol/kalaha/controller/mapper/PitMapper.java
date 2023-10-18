package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.PitDTO;
import com.bol.kalaha.model.Pit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PitMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "numOfStones", target = "numberOfStones")
    PitDTO toDto(Pit pit);
}
