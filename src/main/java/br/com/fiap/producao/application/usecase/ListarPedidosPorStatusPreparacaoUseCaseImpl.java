package br.com.fiap.producao.application.usecase;

import java.util.List;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public class ListarPedidosPorStatusPreparacaoUseCaseImpl implements ListarPedidosPorStatusPreparacaoUseCase {

    private final PedidoCozinhaGateway pedidoCozinhaGateway;

    public ListarPedidosPorStatusPreparacaoUseCaseImpl(PedidoCozinhaGateway pedidoCozinhaGateway) {
        this.pedidoCozinhaGateway = pedidoCozinhaGateway;
    }

    @Override
    public List<Pedido> listar(StatusPreparacao statusPreparacao) {
       return pedidoCozinhaGateway.buscarPorStatusPreparacao(statusPreparacao);
    }

}
