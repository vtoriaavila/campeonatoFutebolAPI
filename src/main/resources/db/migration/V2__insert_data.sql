-- Estádios
INSERT INTO estadio (id, nome, endereco) VALUES (1, 'Maracanã', 'Rio de Janeiro');
INSERT INTO estadio (id, nome, endereco) VALUES (2, 'São Januário', 'Rio de Janeiro');

-- Times
INSERT INTO time (id, nome, sede_id) VALUES (1, 'Flamengo', 1);
INSERT INTO time (id, nome, sede_id) VALUES (2, 'Vasco', 2);

-- Jogadores
INSERT INTO jogador (id, nome, nascimento, altura, time_em_que_joga_id) VALUES
  (1, 'Pedro', '1997-06-20', 1.85, 1),
  (2, 'Gabigol', '1996-08-30', 1.76, 1),
  (3, 'Payet', '1987-03-29', 1.75, 2),
  (4, 'Vegetti', '1988-10-01', 1.83, 2);

-- Campeonato
INSERT INTO campeonato (id, nome, ano) VALUES (1, 'Brasileirão 2024', 2024);

-- Resultados
INSERT INTO resultado (id, num_gols_mandante, num_gols_visitante) VALUES (1, 2, 1);
INSERT INTO resultado (id, num_gols_mandante, num_gols_visitante) VALUES (2, 0, 0);

-- Partidas
INSERT INTO partida (id, data, time_mandante_id, time_visitante_id, campeonato_id, resultado_id) VALUES
  (1, CURRENT_DATE - INTERVAL '10 days', 1, 2, 1, 1),
  (2, CURRENT_DATE - INTERVAL '5 days', 2, 1, 1, 2),
  (3, CURRENT_DATE + INTERVAL '5 days', 1, 2, 1, NULL);
