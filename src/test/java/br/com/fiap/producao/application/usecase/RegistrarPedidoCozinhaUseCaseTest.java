package br.com.fiap.producao.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.api.dto.ItemPedidoRequest;
import br.com.fiap.producao.infra.api.dto.PedidoRequest;

class RegistrarPedidoCozinhaUseCaseTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PedidoCozinhaGateway pedidoCozinhaGateway;

    @Captor
    private ArgumentCaptor<Pedido> pedidoCaptor;

    private RegistrarPedidoCozinhaUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new RegistrarPedidoCozinhaUseCaseImpl(pedidoCozinhaGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void deveRegistrarPedidoNaCozinha() {
        ItemPedidoRequest item1 = new ItemPedidoRequest("X-Salada", 1, null);
        PedidoRequest pedidoRequest = new PedidoRequest(LocalDateTime.now(), 1010L, "Fellipe Bastos",
                Arrays.asList(item1));

        when(pedidoCozinhaGateway.registrar(any()))
                .thenAnswer(i -> i.getArgument(0));

        usecase.registrar(pedidoRequest);

        verify(pedidoCozinhaGateway).registrar(pedidoCaptor.capture());
        Pedido pedidoRegistrado = pedidoCaptor.getValue();

        // Assert - validação do pedido criado
        assertNotNull(pedidoRegistrado);
        assertEquals(pedidoRequest.dataCriacao(), pedidoRegistrado.getDataCriacao());
        assertEquals(pedidoRequest.numeroPedido(), pedidoRegistrado.getNumeroPedido());
        assertEquals(pedidoRequest.cliente(), pedidoRegistrado.getCliente());
        assertEquals(StatusPreparacao.PENDENTE, pedidoRegistrado.getStatus());
        assertEquals(1, pedidoRegistrado.getItens().size());

        // Validando o item dentro do pedido
        ItemPedido itemRegistrado = pedidoRegistrado.getItens().get(0);
        assertEquals(item1.produto(), itemRegistrado.produto());
        assertEquals(item1.quantidade(), itemRegistrado.quantidade());
        assertEquals(item1.observacao(), itemRegistrado.observacao());
    }

}
