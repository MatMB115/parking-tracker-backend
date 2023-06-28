CREATE TABLE tb_autuacao(
    id_autuacao serial PRIMARY KEY,
    id_agente Integer NOT NULL,
    placa text NOT NULL,
    FOREIGN KEY (id_agente) REFERENCES tb_agente(id_agente)
)