package br.com.fiap.producao.infra.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public record PedidoResponse(
        LocalDateTime dataCriacao,
        Long numeroPedido,
        String cliente,
        List<ItemPedidoResponse> itens,
        StatusPreparacao status) {

}
