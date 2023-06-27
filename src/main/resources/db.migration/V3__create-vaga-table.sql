CREATE TABLE tb_vagas(
    id_vaga serial NOT NULL PRIMARY KEY,
    id_veiculo INTEGER,
    id_cliente INTEGER,
    status INTEGER DEFAULT 0,
    position_lat double precision NOT NULL,
    position_long double precision NOT NULL,
    last_modificated TIMESTAMP NOT NULL DEFAULT now(),
    reservation_time TIMESTAMP,
    FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo(id_veiculo)
)