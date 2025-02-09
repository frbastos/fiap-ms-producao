package br.com.fiap.producao.application.usecase;

import java.util.List;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public interface ListarPedidosPorStatusPreparacaoUseCase {

    List<Pedido> listar(StatusPreparacao statusPreparacao);

}
