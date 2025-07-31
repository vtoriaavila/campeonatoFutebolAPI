package edu.ifma.SI.LPWeb.campeonatoFutebol.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partida {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;

    @ManyToOne
    private Time timeMandante;

    @ManyToOne
    private Time timeVisitante;

    @ManyToOne
    private Campeonato campeonato;

    @OneToOne(cascade = CascadeType.ALL)
    private Resultado resultado;
}