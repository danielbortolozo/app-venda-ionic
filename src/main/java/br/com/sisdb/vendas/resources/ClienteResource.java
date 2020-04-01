package br.com.sisdb.vendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.services.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Long id ) throws ObjectNotFoundException {
		
		Cliente obj = service.find(id);		
		return ResponseEntity.ok().body(obj);
	}
	
}
