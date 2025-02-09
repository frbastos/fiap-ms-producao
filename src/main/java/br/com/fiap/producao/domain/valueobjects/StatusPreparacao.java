package br.com.fiap.producao.domain.valueobjects;

public enum StatusPreparacao {
    PENDENTE,    // Pedido está aguardando inicio da preparação
    PREPARANDO,  // Pedido está sendo preparado
    PRONTO,      // Pedido finalizado pelo cozinha
    FINALIZADO    // Pedido entregue para o cliente
}
