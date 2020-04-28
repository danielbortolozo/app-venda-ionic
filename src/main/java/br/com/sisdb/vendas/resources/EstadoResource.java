package br.com.sisdb.vendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sisdb.vendas.domains.Cidade;
import br.com.sisdb.vendas.domains.Estado;
import br.com.sisdb.vendas.dto.CidadeDTO;
import br.com.sisdb.vendas.dto.EstadoDTO;
import br.com.sisdb.vendas.services.CidadeService;
import br.com.sisdb.vendas.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired 
	private CidadeService cidadeService;
	
	
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {			
		List<Estado> list = estadoService.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody EstadoDTO dto){		
		Estado estado = estadoService.fromDTO(dto);
		estado = estadoService.salvar(estado);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(estado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value="/{id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Long id) {
        List<Cidade> list = cidadeService.findByEstado(id);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj))
        		                      .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
		
	}
	
}





