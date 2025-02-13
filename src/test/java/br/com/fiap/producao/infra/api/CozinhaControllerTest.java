package br.com.fiap.producao.infra.api;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.producao.application.usecase.AlterarStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.BuscarPedidoPorNumeroUseCase;
import br.com.fiap.producao.application.usecase.ListarPedidosPorStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.RegistrarPedidoCozinhaUseCase;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.api.dto.ItemPedidoRequest;
import br.com.fiap.producao.infra.api.dto.PedidoRequest;
import br.com.fiap.producao.shared.PedidoHelper;

class CozinhaControllerTest {

        private MockMvc mockMvc;

        private AutoCloseable autoCloseable;

        @Mock
        private RegistrarPedidoCozinhaUseCase registrarPedidoCozinhaUseCase;

        @Mock
        private BuscarPedidoPorNumeroUseCase buscarPedidoPorNumeroUseCase;

        @Mock
        private AlterarStatusPreparacaoUseCase alterarStatusPreparacaoUsecase;

        @Mock
        private ListarPedidosPorStatusPreparacaoUseCase listarPedidosPorStatusPreparacaoUseCase;

        private CozinhaController cozinhaController;

        @BeforeEach
        void setUp() {
                autoCloseable = MockitoAnnotations.openMocks(this);
                cozinhaController = new CozinhaController(
                                registrarPedidoCozinhaUseCase,
                                buscarPedidoPorNumeroUseCase,
                                alterarStatusPreparacaoUsecase,
                                listarPedidosPorStatusPreparacaoUseCase);
                mockMvc = MockMvcBuilders.standaloneSetup(cozinhaController)
                                .build();
        }

        @AfterEach
        void tearDown() throws Exception {
                autoCloseable.close();
        }

        @Test
        void deveRegistrarNovoPedido() throws Exception {

                Pedido pedido = PedidoHelper.gerar();
                ItemPedido itemPedido = pedido.getItens().get(0);

                ItemPedidoRequest item1 = new ItemPedidoRequest(itemPedido.produto(), itemPedido.quantidade(),
                                itemPedido.observacao());
                PedidoRequest pedidoRequest = new PedidoRequest(pedido.getDataCriacao(), pedido.getNumeroPedido(),
                                pedido.getCliente(), Arrays.asList(item1));

                doNothing().when(registrarPedidoCozinhaUseCase).registrar(pedidoRequest);

                mockMvc.perform(
                                post("/cozinha/pedido")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(asJsonString(pedidoRequest)))
                                .andExpect(status().isOk());

                verify(registrarPedidoCozinhaUseCase).registrar(pedidoRequest);

        }

        @Test
        void deveAtualizarStatusPreparacao() throws Exception {

                StatusPreparacao statusPreparacao = StatusPreparacao.PENDENTE;

                Pedido pedido = PedidoHelper.gerar();
                ItemPedido itemPedido = pedido.getItens().get(0);

                ItemPedidoRequest item1 = new ItemPedidoRequest(itemPedido.produto(), itemPedido.quantidade(),
                                itemPedido.observacao());
                PedidoRequest pedidoRequest = new PedidoRequest(pedido.getDataCriacao(), pedido.getNumeroPedido(),
                                pedido.getCliente(), Arrays.asList(item1));

                when(buscarPedidoPorNumeroUseCase.buscar(pedidoRequest.numeroPedido()))
                                .thenReturn(pedido);

                mockMvc.perform(
                                patch("/cozinha/pedido/{numeroPedido}/status", pedido.getNumeroPedido())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .param("status", statusPreparacao.toString())

                )
                                .andExpect(status().isOk());

                verify(buscarPedidoPorNumeroUseCase).buscar(pedido.getNumeroPedido());
                verify(alterarStatusPreparacaoUsecase).alterar(pedido, statusPreparacao);

        }

        @Test
        void devePermitirListarPedidosPorStatus() throws Exception {

                StatusPreparacao statusPreparacao = StatusPreparacao.PREPARANDO;
                Pedido pedido = PedidoHelper.gerar();

                when(listarPedidosPorStatusPreparacaoUseCase.listar(statusPreparacao))
                                .thenReturn(Arrays.asList(pedido));

                mockMvc.perform(
                                get("/cozinha/pedidos")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .param("status", statusPreparacao.toString()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.length()").value(1))
                                .andExpect(jsonPath("$[0].numeroPedido").value(pedido.getNumeroPedido()))
                                .andExpect(jsonPath("$[0].cliente").value(pedido.getCliente()))
                                .andExpect(jsonPath("$[0].status").value(pedido.getStatus().toString()));

        }

        @Test
        void deveBuscarPorNumeroPedido() throws Exception{
                Pedido pedido = PedidoHelper.gerar();

                when(buscarPedidoPorNumeroUseCase.buscar(pedido.getNumeroPedido()))
                        .thenReturn(pedido);
               
                mockMvc.perform(
                        get("/cozinha/pedido/{numeroPedido}", pedido.getNumeroPedido())
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.numeroPedido").value(pedido.getNumeroPedido()))
                        .andExpect(jsonPath("$.cliente").value(pedido.getCliente()))
                        .andExpect(jsonPath("$.status").value(pedido.getStatus().toString()));
        }

        public static String asJsonString(final Object obj) {
                try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.findAndRegisterModules();
                        return objectMapper.writeValueAsString(obj);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

}
