CREATE TABLE tb_veiculo(
                           id_veiculo serial NOT NULL PRIMARY KEY,
                           id_cliente Integer NOT NULL,
                           placa text UNIQUE NOT NULL,
                           modelo text NOT NULL,
                           cor text NOT NULL,
                           FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE
)