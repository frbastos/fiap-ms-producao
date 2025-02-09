package br.com.fiap.producao.infra.mapper;

import java.util.List;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.infra.entities.ItemPedidoEntity;
import br.com.fiap.producao.infra.entities.PedidoEntity;

public class PedidoMapperEntity {

    public static Pedido toObject(PedidoEntity entity) {
        var itensPedido = entity.getItens().stream()
                .map(e -> new ItemPedido(e.getId(), e.getProduto(), e.getQuantidade(), e.getObservacao())).toList();

        return new Pedido(entity.getDataCriacao(), entity.getNumeroPedido(), entity.getCliente(), itensPedido,
                entity.getStatusPreparacao());
    }

    public static PedidoEntity toEntity(Pedido pedido) {
        List<ItemPedidoEntity> itens = pedido.getItens().stream()
                .map(i -> new ItemPedidoEntity(i.id(), i.produto(), i.quantidade(), i.observacao())).toList();
        return new PedidoEntity(pedido.getNumeroPedido(), pedido.getDataCriacao(), pedido.getCliente(), itens, pedido.getStatus());
    }

}
