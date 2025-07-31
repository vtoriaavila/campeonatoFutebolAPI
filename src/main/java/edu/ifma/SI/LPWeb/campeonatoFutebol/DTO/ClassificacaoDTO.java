package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

public record ClassificacaoDTO(
        String nomeTime,
        int jogos,
        int vitorias,
        int empates,
        int derrotas,
        int golsPro,
        int golsContra,
        int pontos,
        int saldoGols
) {}