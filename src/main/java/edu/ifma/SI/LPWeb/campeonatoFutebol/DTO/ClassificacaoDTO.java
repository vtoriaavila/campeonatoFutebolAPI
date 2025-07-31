package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoDTO {
    private String nomeTime;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsPro;
    private int golsContra;
    private int pontos;
    private int saldoGols;
}
