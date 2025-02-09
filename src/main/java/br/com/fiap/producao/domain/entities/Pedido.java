package br.com.fiap.producao.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.producao.domain.valueobjects.ItemPedido;
import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pedido {

        private LocalDateTime dataCriacao;
        private Long numeroPedido;
        private String cliente;
        private List<ItemPedido> itens;
        private StatusPreparacao status;

        public void alterarStatusPreparacao(StatusPreparacao status) {
                this.status = status;
        }
}
