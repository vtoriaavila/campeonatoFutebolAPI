package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {
    private Integer id;
    private int numGolsMandante;
    private int numGolsVisitante;
}
