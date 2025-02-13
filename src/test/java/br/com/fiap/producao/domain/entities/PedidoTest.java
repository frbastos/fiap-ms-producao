package br.com.fiap.producao.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.shared.PedidoHelper;

class PedidoTest {

    @Test
    void test_update_status_with_valid_value() {
        Pedido pedido = PedidoHelper.gerar();
        StatusPreparacao novoStatus = StatusPreparacao.PRONTO;

        pedido.alterarStatusPreparacao(novoStatus);

        assertEquals(StatusPreparacao.PRONTO, pedido.getStatus());
    }

    @Test
    void test_update_status_with_null_value() {
        Pedido pedido = PedidoHelper.gerar();

        pedido.alterarStatusPreparacao(null);

        assertNull(pedido.getStatus());
    }

}
