package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {
    private Integer id;
    private LocalDate data;
    private Time timeMandante;
    private Time timeVisitante;
    private Campeonato campeonato;
    private Resultado resultado;
}
