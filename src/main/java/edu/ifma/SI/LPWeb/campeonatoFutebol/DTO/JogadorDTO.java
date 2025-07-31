package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;

import java.time.LocalDate;

public record JogadorDTO(Integer id,
        String nome,
        LocalDate nascimento, Float altura, Time timeEmQueJoga) {
}
