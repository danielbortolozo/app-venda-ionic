package br.com.sisdb.vendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domains.Pedido;
import br.com.sisdb.vendas.services.PedidoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Long id ) throws ObjectNotFoundException {
		
		Pedido obj = service.buscar(id);		
		return ResponseEntity.ok().body(obj);
	}
	
}