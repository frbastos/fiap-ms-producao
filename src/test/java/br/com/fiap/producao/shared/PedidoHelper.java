package br.com.fiap.producao.shared;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.fiap.producao.domain.entities.Pedido;
import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;

public class PedidoHelper {

    public static Pedido gerar() {
        ItemPedido item1 = new ItemPedido(1010L, "X-Salada", 1, null);
        ItemPedido item2 = new ItemPedido(1010L, "Coca-Cola", 1, null);
        return new Pedido(LocalDateTime.now(), 1010L, "Fellipe Bastos", Arrays.asList(item1, item2),
                StatusPreparacao.PREPARANDO);
    }

}
