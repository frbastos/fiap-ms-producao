package br.com.fiap.producao.infra.api.dto;

public record ItemPedidoResponse(
        String produto,
        int quantidade,
        String observacao) {

}
