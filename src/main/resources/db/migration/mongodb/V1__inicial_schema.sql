CREATE TABLE IF NOT EXISTS pedido (
    numero_pedido BIGINT PRIMARY KEY,
    data_criacao DATETIME NOT NULL,
    cliente VARCHAR(255),
    status_preparacao VARCHAR(20) NOT NULL,
    PRIMARY KEY (numero_pedido)
);

CREATE TABLE IF NOT EXISTS item_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL,
    observacao VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pedido_itens (
    pedido_id BIGINT NOT NULL,
    item_pedido_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, item_pedido_id),
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(numero_pedido) ON DELETE CASCADE,
    CONSTRAINT fk_item_pedido FOREIGN KEY (item_pedido_id) REFERENCES item_pedido(id) ON DELETE CASCADE
);