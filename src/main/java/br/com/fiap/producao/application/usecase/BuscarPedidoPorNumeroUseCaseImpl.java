package br.com.fiap.producao.application.usecase;

import br.com.fiap.producao.application.exception.NaoEnconradoException;
import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;

public class BuscarPedidoPorNumeroUseCaseImpl implements BuscarPedidoPorNumeroUseCase {

    private final PedidoCozinhaGateway pedidoCozinhaGateway;

    public BuscarPedidoPorNumeroUseCaseImpl(PedidoCozinhaGateway pedidoCozinhaGateway) {
        this.pedidoCozinhaGateway = pedidoCozinhaGateway;
    }

    @Override
    public Pedido buscar(Long numeroPedido) {
        return pedidoCozinhaGateway.buscarPedidoPorNumero(numeroPedido)
            .orElseThrow(() -> new NaoEnconradoException("Pedido não encontrado com esse número: " + numeroPedido));
    }

}
