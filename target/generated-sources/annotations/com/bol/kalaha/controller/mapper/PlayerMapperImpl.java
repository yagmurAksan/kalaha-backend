package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.PlayerDTO;
import com.bol.kalaha.model.Player;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-18T14:46:04+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerDTO toDto(Player player) {
        if ( player == null ) {
            return null;
        }

        int id = 0;

        id = player.getId();

        PlayerDTO playerDTO = new PlayerDTO( id );

        return playerDTO;
    }
}
