package edu.ifma.SI.LPWeb.campeonatoFutebol.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campeonato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int ano;
    private String nome;

    @OneToMany(mappedBy = "campeonato")
    private List<Partida> partidas;
}

