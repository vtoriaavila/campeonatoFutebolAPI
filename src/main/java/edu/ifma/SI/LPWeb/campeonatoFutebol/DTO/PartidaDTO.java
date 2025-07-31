package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {
    private Integer id;

    @NotNull(message = "Data da partida é obrigatória")
    private LocalDate data;

    @NotNull(message = "Time mandante é obrigatório")
    private Integer timeMandanteId;

    @NotNull(message = "Time visitante é obrigatório")
    private Integer timeVisitanteId;

    @NotNull(message = "Campeonato é obrigatório")
    private Integer campeonatoId;
}