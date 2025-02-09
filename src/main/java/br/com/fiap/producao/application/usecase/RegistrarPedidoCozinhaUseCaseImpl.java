package br.com.fiap.producao.application.usecase;

import java.util.List;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.api.dto.PedidoRequest;

public class RegistrarPedidoCozinhaUseCaseImpl implements RegistrarPedidoCozinhaUseCase {

    private final PedidoCozinhaGateway pedidoCozinhaGateway;

    public RegistrarPedidoCozinhaUseCaseImpl(PedidoCozinhaGateway pedidoCozinhaGateway) {
        this.pedidoCozinhaGateway = pedidoCozinhaGateway;
    }

    @Override
    public void registrar(PedidoRequest request) {

        List<ItemPedido> itens = request.itens().stream()
                .map(i -> new ItemPedido(null, i.produto(), i.quantidade(), i.observacao())).toList();

        Pedido pedido = new Pedido(request.dataCriacao(), request.numeroPedido(), request.cliente(), itens,
                StatusPreparacao.PENDENTE);

        pedidoCozinhaGateway.registrar(pedido);
    }

}
