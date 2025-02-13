package br.com.fiap.producao.infra.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.infra.entities.PedidoEntity;
import br.com.fiap.producao.infra.mapper.PedidoMapperEntity;
import br.com.fiap.producao.infra.repository.PedidoCozinhaRepository;
import br.com.fiap.producao.shared.PedidoHelper;

public class PedidoCozinhaGatewayTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PedidoCozinhaRepository repository;

    private PedidoCozinhaGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        gateway = new PedidoCozinhaGatewayImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirRegistrarPedido() {
        Pedido pedido = PedidoHelper.gerar();
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);

        when(repository.save(any(PedidoEntity.class)))
                .thenReturn(entity);

        Pedido pedidoCriado = gateway.registrar(pedido);

        assertNotNull(pedidoCriado);
        assertEquals(pedido.getNumeroPedido(), pedidoCriado.getNumeroPedido());
        assertEquals(pedido.getStatus(), pedidoCriado.getStatus());
        verify(repository).save(any(PedidoEntity.class));
    }

    @Test
    void devePermitirBuscaPedidoPorNumero() {

        Pedido pedido = PedidoHelper.gerar();
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);

        when(repository.findById(pedido.getNumeroPedido()))
                .thenReturn(Optional.of(entity));

        gateway.buscarPedidoPorNumero(pedido.getNumeroPedido());

        verify(repository).findById(pedido.getNumeroPedido());
    }

    @Test
    void devePermitirAtualizarPedido() {
        Pedido pedido = PedidoHelper.gerar();
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);

        when(repository.save(any(PedidoEntity.class)))
                .thenReturn(entity);

        Pedido pedidoCriado = gateway.registrar(pedido);

        assertNotNull(pedidoCriado);
        assertEquals(pedido.getNumeroPedido(), pedidoCriado.getNumeroPedido());
        assertEquals(pedido.getStatus(), pedidoCriado.getStatus());
        verify(repository).save(any(PedidoEntity.class));
    }

    @Test
    void devePermitirBuscarPorStatusPreparacao() {
        Pedido pedido = PedidoHelper.gerar();
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);


        when(repository.findByStatusPreparacao(pedido.getStatus()))
            .thenReturn(List.of(entity));

        List<Pedido> resultado = gateway.buscarPorStatusPreparacao(pedido.getStatus());

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

}
