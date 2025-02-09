package br.com.fiap.producao.infra.api.dto;

public record ItemPedidoRequest(
        String produto,
        int quantidade,
        String observacao) {
}