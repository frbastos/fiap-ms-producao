package br.com.fiap.producao.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.producao.application.gateway.PedidoCozinhaGateway;
import br.com.fiap.producao.application.usecase.AlterarStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.AlterarStatusPreparacaoUseCaseImpl;
import br.com.fiap.producao.application.usecase.BuscarPedidoPorNumeroUseCase;
import br.com.fiap.producao.application.usecase.BuscarPedidoPorNumeroUseCaseImpl;
import br.com.fiap.producao.application.usecase.ListarPedidosPorStatusPreparacaoUseCase;
import br.com.fiap.producao.application.usecase.ListarPedidosPorStatusPreparacaoUseCaseImpl;
import br.com.fiap.producao.application.usecase.RegistrarPedidoCozinhaUseCase;
import br.com.fiap.producao.application.usecase.RegistrarPedidoCozinhaUseCaseImpl;

@Configuration
public class BeanConfigurationPedido {

    @Bean
    RegistrarPedidoCozinhaUseCase registrarPedidoCozinhaUseCase(PedidoCozinhaGateway pedidoCozinhaGateway) {
        return new RegistrarPedidoCozinhaUseCaseImpl(pedidoCozinhaGateway);
    }

    @Bean
    BuscarPedidoPorNumeroUseCase buscarPedidoPorNumeroUseCase(PedidoCozinhaGateway pedidoCozinhaGateway) {
        return new BuscarPedidoPorNumeroUseCaseImpl(pedidoCozinhaGateway);
    }

    @Bean
    AlterarStatusPreparacaoUseCase alterarStatusPreparacaoUsecase(PedidoCozinhaGateway pedidoCozinhaGateway) {
        return new AlterarStatusPreparacaoUseCaseImpl(pedidoCozinhaGateway);
    }

    @Bean
    ListarPedidosPorStatusPreparacaoUseCase listarPedidosPorStatusPreparacaoUseCase(
            PedidoCozinhaGateway pedidoCozinhaGateway) {
        return new ListarPedidosPorStatusPreparacaoUseCaseImpl(pedidoCozinhaGateway);
    }

}
