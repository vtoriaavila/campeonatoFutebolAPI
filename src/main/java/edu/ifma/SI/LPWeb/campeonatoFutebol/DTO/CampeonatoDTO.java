package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampeonatoDTO {
    private Integer id;
    private String nome;
    private Integer ano;
}