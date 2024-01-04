package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.PitDTO;
import com.bol.kalaha.model.Pit;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-04T03:05:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class PitMapperImpl implements PitMapper {

    @Override
    public PitDTO toDto(Pit pit) {
        if ( pit == null ) {
            return null;
        }

        int numberOfStones = 0;
        int id = 0;

        numberOfStones = pit.getNumOfStones();
        id = pit.getId();

        PitDTO pitDTO = new PitDTO( id, numberOfStones );

        return pitDTO;
    }
}
