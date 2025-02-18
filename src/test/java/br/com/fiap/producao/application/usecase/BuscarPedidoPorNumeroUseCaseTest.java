package br.com.fiap.producao.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.producao.application.exception.NaoEnconradoException;
import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.shared.PedidoHelper;

class BuscarPedidoPorNumeroUseCaseTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PedidoCozinhaGateway pedidoCozinhaGateway;

    private BuscarPedidoPorNumeroUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new BuscarPedidoPorNumeroUseCaseImpl(pedidoCozinhaGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirBuscarPedidoPorNumero() {
        Pedido pedido = PedidoHelper.gerar();

        when(pedidoCozinhaGateway.buscarPedidoPorNumero(pedido.getNumeroPedido()))
                .thenReturn(Optional.of(pedido));

        Pedido pedidoEncontrado = usecase.buscar(pedido.getNumeroPedido());

        verify(pedidoCozinhaGateway).buscarPedidoPorNumero(anyLong());
        assertNotNull(pedidoEncontrado);

    }

    @Test
    void deveGerarExcecao_BuscarPedidoPorNumero_NaoEncontrado() {
        Pedido pedido = PedidoHelper.gerar();
        Long numeroPedido = pedido.getNumeroPedido();

        when(pedidoCozinhaGateway.buscarPedidoPorNumero(numeroPedido))
                .thenReturn(Optional.empty());

        NaoEnconradoException thrown = assertThrows(
                NaoEnconradoException.class,
                () -> usecase.buscar(numeroPedido));

        assertEquals("Pedido não encontrado com esse número: " + numeroPedido, thrown.getMessage());
    }

}
