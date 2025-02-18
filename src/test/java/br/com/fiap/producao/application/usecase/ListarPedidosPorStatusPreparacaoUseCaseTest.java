package br.com.fiap.producao.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.shared.PedidoHelper;

class ListarPedidosPorStatusPreparacaoUseCaseTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PedidoCozinhaGateway pedidoCozinhaGateway;

    private ListarPedidosPorStatusPreparacaoUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new ListarPedidosPorStatusPreparacaoUseCaseImpl(pedidoCozinhaGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void deveListarPedidosPorStatus() {

        StatusPreparacao statusPreparacao = StatusPreparacao.PRONTO;
        Pedido pedido1 = PedidoHelper.gerar();
        Pedido pedido2 = PedidoHelper.gerar();

        when(pedidoCozinhaGateway.buscarPorStatusPreparacao(statusPreparacao))
                .thenReturn(Arrays.asList(pedido1, pedido2));

        List<Pedido> resultado = usecase.listar(statusPreparacao);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(pedido1));
        assertTrue(resultado.contains(pedido2));

    }

}
