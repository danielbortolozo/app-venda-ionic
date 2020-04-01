package br.com.sisdb.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sisdb.vendas.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
