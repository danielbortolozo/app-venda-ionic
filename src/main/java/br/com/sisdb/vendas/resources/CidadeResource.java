package br.com.sisdb.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domains.Cidade;
import br.com.sisdb.vendas.services.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cidadeService;
	
	
	
	@GetMapping
	public ResponseEntity<List<Cidade>> findAll() {		
		List<Cidade> list = cidadeService.findAll();		
		return ResponseEntity.ok().body(list);		
	}
	
	
	
	
}
