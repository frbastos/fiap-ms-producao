package br.com.fiap.producao.infra.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.entities.PedidoEntity;
import br.com.fiap.producao.infra.feignclient.PedidoFeignClient;
import br.com.fiap.producao.infra.mapper.PedidoMapperEntity;
import br.com.fiap.producao.infra.repository.PedidoCozinhaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoCozinhaGatewayImpl implements PedidoCozinhaGateway{

    private final PedidoCozinhaRepository pedidoCozinhaRepository;
    private final PedidoFeignClient pedidoFeignClient;

    @Override
    public Pedido registrar(Pedido pedido) {
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);
        pedidoCozinhaRepository.save(entity);
        return PedidoMapperEntity.toObject(entity);
    }

    @Override
    public Optional<Pedido> buscarPedidoPorNumero(Long numeroPedido) {
        return pedidoCozinhaRepository.findById(numeroPedido).map(PedidoMapperEntity::toObject);
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        PedidoEntity entity = PedidoMapperEntity.toEntity(pedido);
        pedidoCozinhaRepository.save(entity);
        pedidoFeignClient.atualizarStatus(pedido.getNumeroPedido(), pedido.getStatus().toString());
        return PedidoMapperEntity.toObject(entity);
    }

    @Override
    public List<Pedido> buscarPorStatusPreparacao(StatusPreparacao statusPreparacao) {
        return pedidoCozinhaRepository.findByStatusPreparacao(statusPreparacao)
            .stream()
            .map(PedidoMapperEntity::toObject)
            .toList();
    }

}
