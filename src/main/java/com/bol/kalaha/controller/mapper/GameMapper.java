package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.GameResponseDTO;
import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PitMapper.class, PlayerMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL )
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    @Mapping(source = "board.pits", target = "pitList")
    @Mapping(source = "board.activePlayer", target = "playerInTurn")
    @Mapping(source = "winner", target = "playerWinner")
    GameResponseDTO toDto(Board board, Player winner);
}
