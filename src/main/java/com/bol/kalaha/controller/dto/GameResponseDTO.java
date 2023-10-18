package com.bol.kalaha.controller.dto;

import java.util.List;

public record GameResponseDTO(List<PitDTO> pitList, PlayerDTO playerInTurn, PlayerDTO playerWinner) {}
