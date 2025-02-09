package br.com.fiap.producao.application.gateway;

import java.util.List;
import java.util.Optional;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public interface PedidoCozinhaGateway {

    Pedido registrar(Pedido pedido);

    Optional<Pedido> buscarPedidoPorNumero(Long numeroPedido);

    Pedido atualizar(Pedido pedido);

    List<Pedido> buscarPorStatusPreparacao(StatusPreparacao statusPreparacao);

}
