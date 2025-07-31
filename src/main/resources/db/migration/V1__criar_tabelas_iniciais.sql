CREATE TABLE IF NOT EXISTS estadio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS time (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sede_id INTEGER REFERENCES estadio(id)
);

CREATE TABLE IF NOT EXISTS jogador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nascimento DATE NOT NULL,
    altura FLOAT CHECK (altura >= 1.0),
    time_em_que_joga_id INTEGER NOT NULL REFERENCES time(id)
);

CREATE TABLE IF NOT EXISTS campeonato (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ano INT NOT NULL
);

CREATE TABLE IF NOT EXISTS resultado (
    id SERIAL PRIMARY KEY,
    num_gols_mandante INT NOT NULL,
    num_gols_visitante INT NOT NULL
);

CREATE TABLE IF NOT EXISTS partida (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    time_mandante_id INTEGER NOT NULL REFERENCES time(id),
    time_visitante_id INTEGER NOT NULL REFERENCES time(id),
    campeonato_id INTEGER NOT NULL REFERENCES campeonato(id),
    resultado_id INTEGER REFERENCES resultado(id)
);