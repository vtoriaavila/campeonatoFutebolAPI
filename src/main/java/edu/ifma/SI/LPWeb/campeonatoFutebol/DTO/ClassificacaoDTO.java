package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

public class ClassificacaoDTO {
    private String nomeTime;
    private int vitorias;
    private int golsPro;
    private int golsContra;

    public ClassificacaoDTO(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public void addVitoria() {
        this.vitorias++;
    }

    public void addGols(int pro, int contra) {
        this.golsPro += pro;
        this.golsContra += contra;
    }

    public int getSaldoGols() {
        return golsPro - golsContra;
    }

    public int getVitorias() {
        return vitorias;
    }

    public String getNomeTime() {
        return nomeTime;
    }
}


