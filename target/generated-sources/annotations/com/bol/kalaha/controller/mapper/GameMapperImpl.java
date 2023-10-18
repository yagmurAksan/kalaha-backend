package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.GameResponseDTO;
import com.bol.kalaha.controller.dto.PitDTO;
import com.bol.kalaha.controller.dto.PlayerDTO;
import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-18T14:46:04+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class GameMapperImpl implements GameMapper {

    private final PitMapper pitMapper = Mappers.getMapper( PitMapper.class );
    private final PlayerMapper playerMapper = Mappers.getMapper( PlayerMapper.class );

    @Override
    public GameResponseDTO toDto(Board board, Player winner) {
        if ( board == null && winner == null ) {
            return null;
        }

        List<PitDTO> pitList = null;
        PlayerDTO playerInTurn = null;
        if ( board != null ) {
            pitList = pitListToPitDTOList( board.getPits() );
            playerInTurn = playerMapper.toDto( board.getActivePlayer() );
        }
        PlayerDTO playerWinner = null;
        playerWinner = playerMapper.toDto( winner );

        GameResponseDTO gameResponseDTO = new GameResponseDTO( pitList, playerInTurn, playerWinner );

        return gameResponseDTO;
    }

    protected List<PitDTO> pitListToPitDTOList(List<Pit> list) {
        if ( list == null ) {
            return null;
        }

        List<PitDTO> list1 = new ArrayList<PitDTO>( list.size() );
        for ( Pit pit : list ) {
            list1.add( pitMapper.toDto( pit ) );
        }

        return list1;
    }
}
