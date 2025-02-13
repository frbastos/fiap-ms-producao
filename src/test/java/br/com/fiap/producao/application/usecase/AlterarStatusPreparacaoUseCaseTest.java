package br.com.fiap.producao.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.shared.PedidoHelper;

class AlterarStatusPreparacaoUseCaseTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PedidoCozinhaGateway pedidoCozinhaGateway;

    @Captor
    private ArgumentCaptor<Pedido> pedidoCaptor;

    private AlterarStatusPreparacaoUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new AlterarStatusPreparacaoUseCaseImpl(pedidoCozinhaGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirAlterarStatusPreparacao() {
        Pedido pedido = PedidoHelper.gerar();
        StatusPreparacao novoStatus = StatusPreparacao.PRONTO;

        when(pedidoCozinhaGateway.atualizar(pedido))
                .thenReturn(pedido);

        usecase.alterar(pedido, novoStatus);

        verify(pedidoCozinhaGateway).atualizar(pedidoCaptor.capture());
       
        Pedido pedidoCapturado = pedidoCaptor.getValue();
        assertEquals(pedidoCapturado.getStatus(), novoStatus);

    }

}
