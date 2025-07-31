package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;

import java.time.LocalDate;

public record PartidaDTO(Integer id, LocalDate data, Time timeMandante,Time timeVisitante, Campeonato campeonato, Resultado resultado) {
}
