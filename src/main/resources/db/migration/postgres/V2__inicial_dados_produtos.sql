INSERT INTO pedido(numero_pedido, data_criacao, cliente, status_preparacao)
VALUES(100000, '2024-09-15 09:30:00', null, 'PENDENTE');

INSERT INTO pedido(numero_pedido, data_criacao, cliente, status_preparacao)
VALUES(200000, '2024-09-15 15:00:00', null, 'PENDENTE');

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('X-Salada', 1, null);

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('Batata Frita', 1, null);

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('Coca-Cola', 1, null);

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('Pudim', 1, null);

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('X-Bacon', 2, null);

INSERT INTO item_pedido(produto, quantidade, observacao)
VALUES('Coca-Cola', 1, null);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (100000, 1);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (100000, 2);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (100000, 3);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (100000, 4);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (200000, 1);

INSERT INTO pedido_itens(pedido_id, item_pedido_id)
VALUES (200000, 2);