package br.com.fiap.producao.infra.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.producao.application.usecase.AlterarStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.BuscarPedidoPorNumeroUseCase;
import br.com.fiap.producao.application.usecase.ListarPedidosPorStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.RegistrarPedidoCozinhaUseCase;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.api.dto.PedidoRequest;
import br.com.fiap.producao.infra.api.dto.PedidoResponse;
import br.com.fiap.producao.infra.api.mapper.PedidoMapperDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cozinha")
@RequiredArgsConstructor
public class CozinhaController {

    private final RegistrarPedidoCozinhaUseCase registrarPedidoCozinhaUseCase;
    private final BuscarPedidoPorNumeroUseCase buscarPedidoPorNumeroUseCase;
    private final AlterarStatusPreparacaoUseCase alterarStatusPreparacaoUsecase;
    private final ListarPedidosPorStatusPreparacaoUseCase listarPedidosPorStatusPreparacaoUseCase;

    @PostMapping("/pedido")
    public ResponseEntity<Void> registrarNovoPedido(@Valid @RequestBody PedidoRequest request) {
        registrarPedidoCozinhaUseCase.registrar(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/pedido/{numeroPedido}/status")
    public ResponseEntity<Void> atualizarStatusPreparacao(
            @PathVariable Long numeroPedido,
            @RequestParam StatusPreparacao status) {

        Pedido pedido = buscarPedidoPorNumeroUseCase.buscar(numeroPedido);
        alterarStatusPreparacaoUsecase.alterar(pedido, status);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/pedidos")
    public List<PedidoResponse> listarPedidosPorStatus(@RequestParam StatusPreparacao status) {
        return listarPedidosPorStatusPreparacaoUseCase.listar(status)
                .stream()
                .map(PedidoMapperDTO::toResponse)
                .toList();
    }

    @GetMapping("/pedido/{numeroPedido}")
    public PedidoResponse buscarPorNumeroPedido(
            @PathVariable Long numeroPedido) {

        Pedido pedido = buscarPedidoPorNumeroUseCase.buscar(numeroPedido);
        return PedidoMapperDTO.toResponse(pedido);
    }

}
