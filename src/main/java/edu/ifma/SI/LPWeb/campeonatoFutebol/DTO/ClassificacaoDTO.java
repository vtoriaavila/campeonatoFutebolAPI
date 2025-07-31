package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassificacaoDTO implements Comparable<ClassificacaoDTO> {
    private String nomeTime;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsPro;
    private int golsContra;
    private int pontos;

    public ClassificacaoDTO(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public int getSaldoGols() {
        return golsPro - golsContra;
    }

    @Override
    public int compareTo(ClassificacaoDTO outro) {
        // Primeiro critério: pontos
        int comparaPontos = Integer.compare(outro.pontos, this.pontos);
        if (comparaPontos != 0) return comparaPontos;

        // Segundo critério: número de vitórias
        int comparaVitorias = Integer.compare(outro.vitorias, this.vitorias);
        if (comparaVitorias != 0) return comparaVitorias;

        // Terceiro critério: saldo de gols
        return Integer.compare(outro.getSaldoGols(), this.getSaldoGols());
    }

    public void addGols(int golsPro, int golsContra) {
        this.golsPro += golsPro;
        this.golsContra += golsContra;
        this.jogos++;

        if (golsPro == golsContra) {
            this.empates++;
            this.pontos += 1;
        } else if (golsPro < golsContra) {
            this.derrotas++;
        }
    }

    public void addVitoria() {
        this.vitorias++;
        this.pontos += 3;
    }

}