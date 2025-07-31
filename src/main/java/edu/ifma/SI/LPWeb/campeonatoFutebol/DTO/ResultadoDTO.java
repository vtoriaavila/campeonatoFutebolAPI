package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {
    @NotNull(message = "Número de gols do mandante é obrigatório")
    @Min(value = 0, message = "Gols não podem ser negativos")
    private Integer numGolsMandante;

    @NotNull(message = "Número de gols do visitante é obrigatório")
    @Min(value = 0, message = "Gols não podem ser negativos")
    private Integer numGolsVisitante;
}