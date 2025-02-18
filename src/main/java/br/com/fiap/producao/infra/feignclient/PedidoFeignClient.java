package br.com.fiap.producao.infra.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pedidosClient", url = "${pedidos.service.url}")
public interface PedidoFeignClient {

    @PutMapping("/pedidos/{numeroPedido}/status")
    ResponseEntity<Void> atualizarStatus(
        @PathVariable Long numeroPedido,
        @RequestParam String valor);

}
