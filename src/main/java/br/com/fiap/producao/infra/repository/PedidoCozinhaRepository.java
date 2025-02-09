package br.com.fiap.producao.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.producao.domain.valueobjects.StatusPreparacao;
import br.com.fiap.producao.infra.entities.PedidoEntity;

public interface PedidoCozinhaRepository extends JpaRepository<PedidoEntity, Long>{

    List<PedidoEntity> findByStatusPreparacao(StatusPreparacao statusPreparacao);

}
