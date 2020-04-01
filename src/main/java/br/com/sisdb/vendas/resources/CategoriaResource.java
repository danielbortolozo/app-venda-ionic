package br.com.sisdb.vendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.services.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Long id ) throws ObjectNotFoundException {
		
		Categoria obj = service.buscar(id);
		
		
		return ResponseEntity.ok().body(obj);
	}
	
}
