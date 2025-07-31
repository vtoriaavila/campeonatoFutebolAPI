package edu.ifma.SI.LPWeb.campeonatoFutebol.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Time {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "timeEmQueJoga")
    private List<Jogador> jogadores;

    @OneToOne
    private Estadio sede;
}