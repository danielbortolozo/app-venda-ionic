package br.com.sisdb.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.dto.ProdutoDTO;
import br.com.sisdb.vendas.resources.utils.URL;
import br.com.sisdb.vendas.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> find(@PathVariable Long id ) {		
		Produto obj = service.find(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(
		   @RequestParam(value="descricao", defaultValue="0")String descricao,
		   @RequestParam(value="categorias", defaultValue="0")String categorias,
		   @RequestParam(value="page", defaultValue="0")Integer page,
		   @RequestParam(value="linesPage", defaultValue="4")Integer linesPerPage,
		   @RequestParam(value="orderBy", defaultValue="descricao")String orderBy,
		   @RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		String nomeDecode = URL.decodeParam(descricao);
		List<Long> ids = URL.decodeIntList(categorias);
		
		Page<Produto> list = service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);	
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	
	
	
}
