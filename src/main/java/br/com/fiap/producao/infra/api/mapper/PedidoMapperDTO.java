package br.com.fiap.producao.infra.api.mapper;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.infra.api.dto.ItemPedidoResponse;
import br.com.fiap.producao.infra.api.dto.PedidoResponse;

public class PedidoMapperDTO {

    public static PedidoResponse toResponse(Pedido pedido){
        var itens = pedido.getItens()
            .stream()
            .map(i -> new ItemPedidoResponse(i.produto(), i.quantidade(), i.observacao()))
            .toList();
        return new PedidoResponse(pedido.getDataCriacao(), pedido.getNumeroPedido(), pedido.getCliente(), itens, pedido.getStatus());
    }

}
