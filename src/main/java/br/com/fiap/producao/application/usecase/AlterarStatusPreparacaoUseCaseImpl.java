package br.com.fiap.producao.application.usecase;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public class AlterarStatusPreparacaoUseCaseImpl implements AlterarStatusPreparacaoUseCase {

    private final PedidoCozinhaGateway pedidoCozinhaGateway;

    public AlterarStatusPreparacaoUseCaseImpl(PedidoCozinhaGateway pedidoCozinhaGateway) {
        this.pedidoCozinhaGateway = pedidoCozinhaGateway;
    }

    @Override
    public void alterar(Pedido pedido, StatusPreparacao status) {
        pedido.alterarStatusPreparacao(status);
        pedidoCozinhaGateway.atualizar(pedido);
    }

}
