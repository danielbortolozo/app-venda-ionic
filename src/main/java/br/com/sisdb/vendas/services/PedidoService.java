package br.com.sisdb.vendas.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Pedido;
import br.com.sisdb.vendas.repositories.PedidoRepository;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@Service
public class PedidoService {

	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscar(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Pedido não encontrado Id: "+id + ", Tipos: "+ Pedido.class.getName()));
	}
	
	
}
