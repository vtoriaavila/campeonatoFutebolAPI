package edu.ifma.SI.LPWeb.campeonatoFutebol.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numGolsMandante;
    private int numGolsVisitante;

    public int getPontuacaoMandante() {
        if (numGolsMandante > numGolsVisitante) return 3;
        if (numGolsMandante == numGolsVisitante) return 1;
        return 0;
    }

    public int getPontuacaoVisitante() {
        if (numGolsVisitante > numGolsMandante) return 3;
        if (numGolsVisitante == numGolsMandante) return 1;
        return 0;
    }

    public boolean jogoSaiuEmpatado() {
        return numGolsMandante == numGolsVisitante;
    }
}