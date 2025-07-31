package edu.ifma.SI.LPWeb.campeonatoFutebol.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jogador")
public class Jogador {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private LocalDate nascimento;
    private float altura;

    @ManyToOne
    private Time timeEmQueJoga;
}