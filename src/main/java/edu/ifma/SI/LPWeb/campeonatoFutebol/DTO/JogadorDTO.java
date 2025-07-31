package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

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
public class JogadorDTO {
    private Integer id;
    private String nome;
    private LocalDate nascimento;
    private Float altura;
    private Time timeEmQueJoga;
}
