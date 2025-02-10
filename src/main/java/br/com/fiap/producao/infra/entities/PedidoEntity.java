package br.com.fiap.producao.infra.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @Column(name = "numero_pedido", unique = true)
    private Long numeroPedido;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "cliente")
    private String cliente;

    @JoinTable(
        name = "pedido_itens",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "item_pedido_id")
    )
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_preparacao")
    private StatusPreparacao statusPreparacao;

    public void preparando() {
        this.statusPreparacao = StatusPreparacao.PREPARANDO;
    }

    public void pronto() {
        this.statusPreparacao = StatusPreparacao.PRONTO;
    }

    public void finalizado() {
        this.statusPreparacao = StatusPreparacao.FINALIZADO;
    }

}
