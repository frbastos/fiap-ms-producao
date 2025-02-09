package br.com.fiap.producao.application.usecase;

import br.com.fiap.producao.domain.entities.Pedido;

public interface BuscarPedidoPorNumeroUseCase {

    Pedido buscar(Long numeroPedido);

}
