package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.PlayerDTO;
import com.bol.kalaha.model.Player;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerMapper {

    PlayerDTO toDto(Player player);
}
