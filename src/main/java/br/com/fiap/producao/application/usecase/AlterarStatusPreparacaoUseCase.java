package br.com.fiap.producao.application.usecase;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public interface AlterarStatusPreparacaoUseCase {

    void alterar(Pedido pedido, StatusPreparacao status);

}
