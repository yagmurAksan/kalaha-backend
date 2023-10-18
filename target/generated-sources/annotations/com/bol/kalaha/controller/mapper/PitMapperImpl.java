package com.bol.kalaha.controller.mapper;

import com.bol.kalaha.controller.dto.PitDTO;
import com.bol.kalaha.model.Pit;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-18T14:46:04+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class PitMapperImpl implements PitMapper {

    @Override
    public PitDTO toDto(Pit pit) {
        if ( pit == null ) {
            return null;
        }

        int id = 0;
        int numberOfStones = 0;

        id = pit.getId();
        numberOfStones = pit.getNumOfStones();

        PitDTO pitDTO = new PitDTO( id, numberOfStones );

        return pitDTO;
    }
}
