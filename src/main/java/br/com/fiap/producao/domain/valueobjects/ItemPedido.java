package br.com.fiap.producao.domain.valueobjects;

public record ItemPedido(
        Long id,
        String produto,
        int quantidade,
        String observacao) {
}
