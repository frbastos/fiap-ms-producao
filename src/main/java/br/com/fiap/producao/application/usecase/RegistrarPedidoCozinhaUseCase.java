package br.com.fiap.producao.application.usecase;

import br.com.fiap.producao.infra.api.dto.PedidoRequest;

public interface RegistrarPedidoCozinhaUseCase {

    void registrar(PedidoRequest request);

}
