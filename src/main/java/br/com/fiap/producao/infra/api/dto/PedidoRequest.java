package br.com.fiap.producao.infra.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequest(
        @NotNull(message = "Data não pode ser vazia") LocalDateTime dataCriacao,
        @NotNull(message = "Número de pedido não pode ser vazio") Long numeroPedido,
        String cliente,
        @Size(min = 1, message = "Deve existir pelo menos um item no pedido") List<ItemPedidoRequest> itens) {
}
