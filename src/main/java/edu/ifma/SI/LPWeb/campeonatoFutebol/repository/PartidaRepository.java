package edu.ifma.SI.LPWeb.campeonatoFutebol.repository;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
    List<Partida> findByDataBefore(LocalDate date);
    List<Partida> findByDataAfter(LocalDate date);
    List<Partida> findByCampeonatoId(Integer campeonatoId);
}
