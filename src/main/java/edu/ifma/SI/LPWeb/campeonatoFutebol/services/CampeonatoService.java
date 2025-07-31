package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.ClassificacaoDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.CampeonatoRepository;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CampeonatoService {
    private final CampeonatoRepository repository;
    private PartidaRepository partidaRepository;

    public CampeonatoService(CampeonatoRepository repository, PartidaRepository partidaRepository) {
        this.repository = repository;
        this.partidaRepository = partidaRepository;
    }

    public List<Campeonato> listarTodos() {
        return repository.findAll();
    }

    public Optional<Campeonato> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Campeonato salvar(Campeonato campeonato) {
        return repository.save(campeonato);
    }

    public Campeonato atualizar(Campeonato campeonato) {
        return repository.save(campeonato);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public Set<Time> listarTimesDoCampeonato(Integer campeonatoId) {
        List<Partida> partidas = partidaRepository.findByCampeonatoId(campeonatoId);
        return partidas.stream()
                .flatMap(p -> Stream.of(p.getTimeMandante(), p.getTimeVisitante()))
                .collect(Collectors.toSet());
    }

    public List<Partida> listarPartidasPassadas(Integer campeonatoId) {
        return partidaRepository.findByCampeonatoId(campeonatoId).stream()
                .filter(p -> p.getData().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<Partida> listarPartidasFuturas(Integer campeonatoId) {
        return partidaRepository.findByCampeonatoId(campeonatoId).stream()
                .filter(p -> p.getData().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<ClassificacaoDTO> listarClassificacao(Integer campeonatoId) {
        Map<Time, Estatisticas> mapa = new HashMap<>();

        List<Partida> partidas = partidaRepository.findByCampeonatoId(campeonatoId);

        for (Partida partida : partidas) {
            Time mandante = partida.getTimeMandante();
            Time visitante = partida.getTimeVisitante();
            Resultado resultado = partida.getResultado();

            if (resultado == null) continue;

            mapa.putIfAbsent(mandante, new Estatisticas(mandante.getNome()));
            mapa.putIfAbsent(visitante, new Estatisticas(visitante.getNome()));

            Estatisticas cMandante = mapa.get(mandante);
            Estatisticas cVisitante = mapa.get(visitante);

            int golsMandante = resultado.getNumGolsMandante();
            int golsVisitante = resultado.getNumGolsVisitante();

            cMandante.registrarPartida(golsMandante, golsVisitante);
            cVisitante.registrarPartida(golsVisitante, golsMandante);
        }

        return mapa.values().stream()
                .map(Estatisticas::toDTO)
                .sorted(Comparator
                        .comparingInt(ClassificacaoDTO::pontos).reversed()
                        .thenComparingInt(ClassificacaoDTO::vitorias).reversed()
                        .thenComparingInt(ClassificacaoDTO::saldoGols).reversed())
                .collect(Collectors.toList());
    }

    private static class Estatisticas {
        private final String nomeTime;
        private int jogos = 0;
        private int vitorias = 0;
        private int empates = 0;
        private int derrotas = 0;
        private int golsPro = 0;
        private int golsContra = 0;
        private int pontos = 0;

        public Estatisticas(String nomeTime) {
            this.nomeTime = nomeTime;
        }

        public void registrarPartida(int golsFeitos, int golsSofridos) {
            this.jogos++;
            this.golsPro += golsFeitos;
            this.golsContra += golsSofridos;

            if (golsFeitos > golsSofridos) {
                this.vitorias++;
                this.pontos += 3;
            } else if (golsFeitos == golsSofridos) {
                this.empates++;
                this.pontos += 1;
            } else {
                this.derrotas++;
            }
        }

        public ClassificacaoDTO toDTO() {
            return new ClassificacaoDTO(
                    nomeTime,
                    jogos,
                    vitorias,
                    empates,
                    derrotas,
                    golsPro,
                    golsContra,
                    pontos,
                    golsPro - golsContra // saldo de gols
            );
        }
    }
}

